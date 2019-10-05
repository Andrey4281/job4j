import com.google.common.base.Joiner;

public class AdvancedInteractCalc extends InteractCalc {

    /**
     * Constructor.
     *
     * @param calculator Object of class Calculator which will be used for calculations.
     * @param console    Object of class Console which will be used for interactive input and output.
     * @param validator
     */
    public AdvancedInteractCalc(Calculator calculator, Console console, Validator validator) {
        super(calculator, console, validator);
    }

    @Override
    protected String prepareListOfOperation() {
        return Joiner.on(LN).join(super.prepareListOfOperation(),
                "Sin (sign of operation is Sin);",
                "Cos (sign of operation is Cos);",
                "Tan (sign of operation is Tan);",
                "CTan (sign of operation is CTan);");
    }

    @Override
    protected double evaluateResultInDependFromTypeOfOperation(double first, String sOperation) {
        if (sOperation.matches(super.listSupportedOperations)) {
            return super.evaluateResultInDependFromTypeOfOperation(first, sOperation);
        } else {
            return evaluateTrigonometricFunctions(first, sOperation);
        }
    }

    private double evaluateTrigonometricFunctions(double argument, String sOperation) {
        double result;
        String sOperationWCase = sOperation.toLowerCase();
        switch (sOperationWCase) {
            case "cos": result = ((EngineerCalculator)calculator).evaluateCos(argument); break;
            case "sin": result = ((EngineerCalculator)calculator).evaluateSin(argument); break;
            case  "tan": result = ((EngineerCalculator)calculator).evaluateTan(argument); break;
            case  "ctan": result = ((EngineerCalculator)calculator).evaluateCTan(argument); break;
            default: result = 0;

        }
        return result;
    }
}
