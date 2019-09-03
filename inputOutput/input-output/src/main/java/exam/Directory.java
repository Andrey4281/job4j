package exam;

public class Directory {
    private static Directory ourInstance = new Directory();
    private String value;

    public static Directory getInstance() {
        return ourInstance;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private Directory() {
    }
}
