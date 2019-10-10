
import java.io.PrintStream;

public class Output {
    private final PrintStream out;

    public Output(PrintStream out) {
        this.out = out;
    }

    void output(String arg) {
        out.println(arg);
    }
}
