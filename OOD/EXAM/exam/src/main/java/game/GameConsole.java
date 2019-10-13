package game;

import java.io.PrintStream;
import java.util.Scanner;

public class GameConsole implements Console {
    private final Scanner in;
    private final PrintStream out;

    public GameConsole(Scanner in, PrintStream out) {
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
