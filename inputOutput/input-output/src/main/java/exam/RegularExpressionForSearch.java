package exam;

public class RegularExpressionForSearch {
    private static RegularExpressionForSearch ourInstance = new RegularExpressionForSearch();
    private String value;

    public static RegularExpressionForSearch getInstance() {
        return ourInstance;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private RegularExpressionForSearch() {
    }
}
