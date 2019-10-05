import com.google.common.base.Joiner;

import java.util.HashMap;
import java.util.Map;

/**
 * Interactive calculator.
 * @author asemenov
 * @since 28.09.2019
 * @version 1
 */
public class InteractCalc {
    protected final String LN = System.getProperty("line.separator");
    private final Calculator calculator;
    private final Console console;
    private final Validator validator;
    private double currentResult = 0;
    protected final Map<String, Operation> supportedBinaryOperations;

    /**
     * Constructor.
     * @param calculator Object of class Calculator which will be used for calculations.
     * @param console Object of class Console which will be used for interactive input and output.
     */
    public InteractCalc(final Calculator calculator, final Console console, final Validator validator) {
        this.calculator = calculator;
        this.console = console;
        this.validator = validator;
        this.supportedBinaryOperations = new HashMap<>();
        this.supportedBinaryOperations.put("+", (double[] args)->{return this.calculator.addition(args[0], args[1]);});
        this.supportedBinaryOperations.put("-", (double[] args)->{return this.calculator.subtraction(args[0], args[1]);});
        this.supportedBinaryOperations.put("*", (double[] args)->{return this.calculator.multiplication(args[0], args[1]);});
        this.supportedBinaryOperations.put("/", (double[] args)->{return this.calculator.division(args[0], args[1]);});
    }


    /**
     * Main loop of interactive calculator.
     */
    public void start() {
        console.output(printHelp());
        String input = console.input(), sOperation = "";
        while (!input.equalsIgnoreCase("exit")) {
            if (!validator.checkArgument(input) && !(validator.checkOperation(input))) {
                throw new ErrorOfInputOutput(Joiner.on(LN).
                        join("You should enter real number for argument or sign of supported operations or exit", ""));
            }

            if (validator.checkArgument(input)) {
                currentResult = Double.parseDouble(input);
                input = console.input();
                if (!validator.checkOperation(input)) {
                    throw new ErrorOfInputOutput(Joiner.on(LN).
                            join("You should enter sign of operations after arguments", ""));
                }
                sOperation = input;
            } else {
                sOperation = input;
            }

            currentResult = evaluateResultInDependFromTypeOfOperation(currentResult, sOperation);
            console.output(Joiner.on(LN).join(String.format("=%.3f", currentResult), ""));

            console.output(Joiner.on(LN).join("Continue your calculation or enter exit", ""));
            input = console.input();
        }
    }

    protected String prepareListOfOperation() {
        return Joiner.on(LN).join("This is interactive calculator",
                "Calcalutor support folowing operations:",
                "Addition (sign of operation is +);",
                "Subtraction (sign of operation is -);",
                "Multiplication (sign of operation is *);",
                "Division (sign of operation is /);");
    }

    protected double evaluateResultInDependFromTypeOfOperation(double first, String sOperation) {
        double second = getArgument();
        return supportedBinaryOperations.get(sOperation).apply(new double[]{first, second});
    }

    /**
     * Method returns information for user
     * @return Help for user.
     */
    private String printHelp() {
        return Joiner.on(LN).join(prepareListOfOperation(),
                "Exit from program (sign of option is exit)",
                "Please enter exit or begin your calculation",
                "");
    }

    private double getArgument() {
        String sArgument = console.input();
        if (!validator.checkArgument(sArgument)) {
            throw new ErrorOfInputOutput(Joiner.on(LN).join("You should enter a real number for second argument", ""));
        }
        return Double.parseDouble(sArgument);
    }

    public static void main(String[] args) {
        InteractCalc interactCalc = new InteractCalc(new Calculator(), new Console(System.in, System.out), new Validator());
        interactCalc.start();
    }

}
