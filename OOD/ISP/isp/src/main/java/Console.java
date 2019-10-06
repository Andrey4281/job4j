public class Console {
    private final Input input;
    private final Output output;


    public Console(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public void output(String arg) {
        output.output(arg);
    }

    public String input() {
        return input.input();
    }
}
