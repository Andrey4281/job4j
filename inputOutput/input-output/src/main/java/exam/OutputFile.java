package exam;

public class OutputFile {
    private static OutputFile ourInstance = new OutputFile();
    private String value;

    public static OutputFile getInstance() {
        return ourInstance;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private OutputFile() {
    }
}
