package archive;

import java.io.File;
import java.util.Arrays;

public class Args {
    private String[] args;

    public Args(final String[] args) {
        this.args = args;
    }

    public File directory() {
        String res = "\\";
        int index = Arrays.asList(args).indexOf("-d");
        if (index != -1) {
            res = args[index + 1];
        }
        return new File(res);
    }

    public String excule() {
        StringBuilder res = new StringBuilder("[^\\.]+");
        int index = Arrays.asList(args).indexOf("-e");
        if (index != -1) {
            res.append("\\").append(args[index + 1].substring(1));
        }
        return res.toString();
    }

    public File output() {
        StringBuilder res = new StringBuilder("./");
        int index = Arrays.asList(args).indexOf("-o");
        if (index != -1) {
            res.append(args[index + 1]);
        }
        return new File(res.toString());
    }
}
