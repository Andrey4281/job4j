import java.io.InputStream;
import java.util.Scanner;

public class Input {
    private final Scanner in;

    public Input(InputStream in) {
        this.in = new Scanner(in);
    }

    public String input() {
        return in.nextLine();
    }
}
