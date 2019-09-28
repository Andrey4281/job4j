import com.google.common.base.Joiner;

/**
 * Class for validation of input of interactive calculator.
 * @author asemenov
 * @since 28.09.2019
 * @version 1
 */
public class Validator {
    private static final String LN = System.getProperty("line.separator");

    /**
     * Method for checking option.
     * @param option Being checked option.
     */
    public void checkOption(String option) {
        if (!option.equalsIgnoreCase("NC") &&
        !option.equalsIgnoreCase("PV") &&
        !option.equalsIgnoreCase("exit")) {
            throw new ErrorOfInputOutput(Joiner.on(LN).join("Invalid option value",
                    "Option must be eqals NC or PV or exit",""));
        }
    }

    /**
     * Method for checking argument.
     * @param argument Being checked argument.
     */
    public void checkArgument(String argument) {
        if (!argument.matches("^[0-9]+\\.{0,1}[0-9]*$")) {
            throw new ErrorOfInputOutput(Joiner.on(LN).join("Invalid argument value",
                    "Argument must be real number", ""));
        }
    }

    public void checkOperation(String operation) {
        if (!operation.matches("[\\+\\-\\*/]")) {
            throw new ErrorOfInputOutput(Joiner.on(LN).join("Invalid operation",
                    "Operation must be equal + or - or * or /", ""));
        }
    }
}
