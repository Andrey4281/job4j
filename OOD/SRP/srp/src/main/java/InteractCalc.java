import com.google.common.base.Joiner;

/**
 * Interactive calculator.
 * @author asemenov
 * @since 28.09.2019
 * @version 1
 */
public class InteractCalc {
    private static final String LN = System.getProperty("line.separator");
    private final Calculator calculator;
    private final Console console;
    private final Validator validator;

    /**
     * Constructor.
     * @param calculator Object of class Calculator which will be used for calculations.
     * @param console Object of class Console which will be used for interactive input and output.
     */
    public InteractCalc(final Calculator calculator, final Console console, final Validator validator) {
        this.calculator = calculator;
        this.console = console;
        this.validator = validator;
    }


    /**
     * Main loop of interactive calculator.
     */
    public void start() {
        console.output(printHelp());
        String option = console.input();
        validator.checkOption(option);
        double first = 0;
        while (!option.equalsIgnoreCase("exit")) {
            if (option.equalsIgnoreCase("NC")) {
                console.output(Joiner.on(LN).join("Enter the first argument", ""));
                first = getArgument();
            }
            console.output(Joiner.on(LN).join("Enter the operation", ""));
            String sOperation = console.input();
            validator.checkOperation(sOperation);

            console.output(Joiner.on(LN).join("Enter the second argument", ""));
            double second = getArgument();

            first = evalulateResult(first, second, sOperation);
            console.output(Joiner.on(LN).join("Result of operation", first, ""));

            console.output(Joiner.on(LN).join("Choose new option NC or PV or exit", ""));
            option = console.input();
            validator.checkOption(option);
        }
    }

    /**
     * Return result of arithmetic operation by sign of operation.
     * @param first first argument.
     * @param second second argument.
     * @param sOperation sign of operation
     * @return
     */
    private double evalulateResult(double first, double second, String sOperation) {
        double result;
        switch (sOperation) {
            case "+": result = calculator.addition(first, second); break;
            case "-": result = calculator.subtraction(first, second); break;
            case  "*": result = calculator.multiplication(first, second); break;
            case  "/": result = calculator.division(first, second); break;
            default: result = 0;
        }
        return result;
    }

    /**
     * Method returns information for user
     * @return Help for user.
     */
    private String printHelp() {
        return Joiner.on(LN).join("This is interactive calculator",
                "Calcalutor support folowing operations:",
                "Addition (sign of operation is +);",
                "Subtraction (sign of operation is -);",
                "Multiplication (sign of operation is *);",
                "Division (sign of operation is /);",
                "Choose one from the next option:",
                "Begin new calculation (sign of option is NC);",
                "Use previous value for calculations (sign of option is PV, default value of previous value is 0);",
                "Exit from program (sign of option is exit)",
                "");
    }

    private double getArgument() {
        String sArgument = console.input();
        validator.checkArgument(sArgument);
        return Double.parseDouble(sArgument);
    }

    public static void main(String[] args) {
        InteractCalc interactCalc = new InteractCalc(new Calculator(), new Console(System.in, System.out), new Validator());
        interactCalc.start();
    }

}
