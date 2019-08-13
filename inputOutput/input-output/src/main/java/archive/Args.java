package archive;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Args {
    private static final String DIRECTORY = "-d";
    private static final String EXCULE = "-e";
    private static final String OUTPUT = "-o";
    private String[] args;

    public Args(final String[] args) {
        this.args = args;
    }

    public Map<String, String> parse() {
        Map<String, String> result = new HashMap<>();
        String directory = "\\";
        StringBuilder excule = new StringBuilder("[^\\.]+");
        StringBuilder output = new StringBuilder("./");

        for (int index = 0; index < args.length; index++) {
            if (args[index].equals(DIRECTORY)) {
                result.put(DIRECTORY, args[index + 1]);
             } else if (args[index].equals(EXCULE)) {
                excule.append("\\").append(args[index + 1].substring(1));
                result.put(EXCULE, excule.toString());
            } else if (args[index].equals(OUTPUT)) {
                output.append(args[index + 1]);
                result.put(OUTPUT, output.toString());
            }
            ++index;
        }
        return result;
    }
}
