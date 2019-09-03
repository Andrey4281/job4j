package exam;

import com.google.common.base.Joiner;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CommandLineAnaluze {
    private static final String FSYMBOLS = "[^/\\?:\\*\\\"\\\\><\\|]+";
    private static final String LN = System.getProperty("line.separator");

    private String[] args;

    public CommandLineAnaluze(final String[] args) {
        this.args = args;
        inicializeKeys();
    }

    public Map<String, String> parse() {
        Map<String, String> map = new HashMap<>();
        if (args.length != 7) {
            throw new InvalidArgumentsException(Joiner.on(LN).join("Error: invalid number of command line parameters",
                    returnMainInformation()));
        } else if (!args[0].equals("-d") || !args[2].equals("-n")
        || !args[4].matches("(-m|-f|-r)") || !args[5].equals("-o")) {
            throw new InvalidArgumentsException(Joiner.on(LN).join("Error: invalid command line arguments",
                    returnMainInformation()));
        } else if (!(new File(args[1]).exists())) {
            throw new InvalidArgumentsException(prepareString("Error: invalid key -d value. Directory is not exists"));
        } else if (args[4].equals("-m") && (!args[3].matches(Joiner.on("").join("\\*\\.", FSYMBOLS)))) {
            throw new InvalidArgumentsException(prepareString("Error: invalid key -n value. Invalid of file mask value."));
        } else if (args[4].equals("-f") && (!args[3].matches(FSYMBOLS))) {
            throw new InvalidArgumentsException(prepareString("Error: invalid key -n value. Invalid of file name value."));
        } else {
            map.put(Directory.getInstance().getValue(), args[1]);
            map.put(RegularExpressionForSearch.getInstance().getValue(), buildPatternForSearch());
            map.put(OutputFile.getInstance().getValue(), args[6]);
        }
        return map;
    }

    private String buildPatternForSearch() {
        StringBuilder res = new StringBuilder();
        if (args[4].equals("-f") | args[4].equals("-r")) {
            res.append(args[3]);
        } else if (args[4].equals("-m")) {
            res.append(FSYMBOLS).append("\\").append(args[3].substring(1));
        }
        return res.toString();
    }

    private String returnMainInformation() {
        return Joiner.on(LN).join("You should determine commands line arguments by following way:",
                "-d directoryForSearch -n criteriaForSearch -[mfr] -o fileForSaveResult",
                "Key -m: Search by mask of file",
                "Key -f: Search by a complete match for a file name",
                "Key -r: Search by regular expression",
                ""
                );
    }

    private String prepareString(String value) {
        return Joiner.on(LN).join(value, "");
    }

    private void inicializeKeys() {
        Directory.getInstance().setValue("-d");
        RegularExpressionForSearch.getInstance().setValue("-n");
        OutputFile.getInstance().setValue("-o");
    }
}
