package exam;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.assertThat;

public class CommandLineAnaluzeTest {
    private static final String LN = System.getProperty("line.separator");
    private static final String EXINF = Joiner.on(LN).join("You should determine commands line arguments by following way:",
            "-d directoryForSearch -n criteriaForSearch -[mfr] -o fileForSaveResult",
            "Key -m: Search by mask of file",
            "Key -f: Search by a complete match for a file name",
            "Key -r: Search by regular expression",
            "");

    @Test
    public void whenGiveEightArgumentsShouldReturnError() {
        String expected = Joiner.on(LN).join("Error: invalid number of command line parameters",
                EXINF
        );

        testToCheckValidateServiceOfCommandLineAnaluze(new String[8], expected);
    }

    @Test
    public void whenGiveInvalidCommandLineArgumentsShouldReturnError() {
        String expected = Joiner.on(LN).join("Error: invalid command line arguments",
                EXINF
        );
        String[] testCommandLineArguments = new String[]{"-d", "/home", "-n", "*.txt", "-a", "-o", "log.txt"};

        testToCheckValidateServiceOfCommandLineAnaluze(testCommandLineArguments, expected);
    }

    @Test
    public void whenGiveInvalidDirectoryShouldReturnError() {
        String expected = Joiner.on(LN).join("Error: invalid key -d value. Directory is not exists", "");
        File root = new File(System.getProperty("java.io.tmpdir"), "search");
        root.mkdir();
        root.delete();
        String[] testCommandLineArguments = new String[]{"-d", root.getAbsolutePath(), "-n", "*.txt", "-m", "-o", "log.txt"};

        testToCheckValidateServiceOfCommandLineAnaluze(testCommandLineArguments, expected);
    }

    @Test
    public void whenGiveInvalidFileMaskShouldReturnError() {
        String expected = Joiner.on(LN).join("Error: invalid key -n value. Invalid of file mask value.", "");
        String[] testCommandLineArguments = new String[]{"-d", "/home", "-n", "*.tx>\\t|", "-m", "-o", "log.txt"};

        testToCheckValidateServiceOfCommandLineAnaluze(testCommandLineArguments, expected);
    }

    @Test
    public void whenGiveInvalidFileNameValueShouldReturnError() {
        String expected = Joiner.on(LN).join("Error: invalid key -n value. Invalid of file name value.", "");
        String[] testCommandLineArguments = new String[]{"-d", "/home", "-n", "my\\file.tx:t", "-f", "-o", "log.txt"};

        testToCheckValidateServiceOfCommandLineAnaluze(testCommandLineArguments, expected);
    }

    @Test
    public void whenGiveValidCommandLineValueShouldReturnMap() {
        String[] testCommandLineArguments = new String[]{"-d", "/home", "-n", "*.txt", "-m",
        "-o", "log.txt"};
        CommandLineAnaluze analuze = new CommandLineAnaluze(testCommandLineArguments);

        Map<String, String> map = analuze.parse();
        assertThat(map.get(Directory.getInstance().getValue())).isEqualTo("/home");
        assertThat(map.get(RegularExpressionForSearch.getInstance().getValue())).
                isEqualTo("[^/\\?:\\*\\\"\\\\><\\|]+\\.txt");
        assertThat(map.get(OutputFile.getInstance().getValue())).isEqualTo("log.txt");
    }

    private void testToCheckValidateServiceOfCommandLineAnaluze(String[] args, String expected) {
        CommandLineAnaluze analuze = new CommandLineAnaluze(args);
        Throwable thrown = catchThrowable(analuze::parse);
        assertThat(thrown).isInstanceOf(InvalidArgumentsException.class);
        assertThat(thrown.getMessage()).isEqualTo(expected);
    }

}