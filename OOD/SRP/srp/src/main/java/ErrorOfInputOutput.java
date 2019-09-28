/**
 * This exception class is used for interactive calculator input and output errors.
 * @author asemenov
 * @since 28.09.2019
 * @version 1
 */
public class ErrorOfInputOutput extends RuntimeException {
    /**
     * Constuctor
     * @param message message of exception
     */
    public ErrorOfInputOutput(String message) {
        super(message);
    }
}
