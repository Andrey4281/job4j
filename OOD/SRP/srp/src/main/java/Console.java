import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * This class is used for input and output of Strings to defined streams.
 * @author asemenov
 * @since 28.09.2019
 * @version 1
 */
public class Console {
    private static Logger LOG = LogManager.getLogger(Console.class);
    private final Scanner reader;
    private final OutputStream writer;

    /**
     * Constructor.
     * @param reader defined stream of input.
     * @param writer defined stream of output.
     */
    public Console(final InputStream reader, final OutputStream writer) {
        this.reader = new Scanner(reader);
        this.writer = writer;
    }

    /**
     * Output method.
     * @param message String for output.
     */
    public void output(String message) {
        try {
            writer.write(message.getBytes());
        } catch (IOException e) {
            LOG.error("Error of output", e);
        }
    }

    /**
     * Input method.
     * @return String being got from output.
     */
    public String input() {
        return reader.nextLine();
    }
}
