import com.google.common.base.Joiner;

/**
 * Class for validation of input of interactive calculator.
 * @author asemenov
 * @since 28.09.2019
 * @version 1
 */
public class Validator {
    private static final String LN = System.getProperty("line.separator");


    public boolean checkArgument(String argument) {
        return argument.matches("^[0-9]+\\.{0,1}[0-9]*$");
    }

    public boolean checkOperation(String operation) {
        return checkOperationByRegExpr(operation, "[\\+\\-\\*/]", "Operation must be equal + or - or * or /");
    }

    protected boolean checkOperationByRegExpr(String operation, String regExpr, String errorMessage) {
        return  operation.toLowerCase().matches(regExpr);
    }
}
