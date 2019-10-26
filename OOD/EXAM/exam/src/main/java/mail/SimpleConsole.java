package mail;

import java.io.PrintStream;
import java.util.Scanner;

public class SimpleConsole implements Console {
    private final Scanner in;
    private final PrintStream out;

    public SimpleConsole(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void output(String message) {
        this.out.print(message);
    }

    @Override
    public String intput() {
        return in.nextLine();
    }
}
