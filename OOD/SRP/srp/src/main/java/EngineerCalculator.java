/**
 * Class engineer calculator.
 * This class support basic arithmetical operations and evaluations of trigonometry functions.
 * @author asemenov
 * @since 29.09.2019
 * @version 1
 */
public class EngineerCalculator extends Calculator {

    /**
     * Evaluation of Sin
     * @param argument an angle in radians.
     * @return result of evaluation
     */
    public double evaluateSin(double argument) {
        return Math.sin(argument);
    }

    /**
     * Evaluation of Cos
     * @param argument an angle in radians.
     * @return result of evaluation
     */
    public double evaluateCos(double argument) {
        return Math.cos(argument);
    }

    /**
     * Evaluation of Tan
     * @param argument an angle in radians.
     * @return result of evaluation
     */
    public double evaluateTan(double argument) {
        return Math.tan(argument);
    }

    /**
     * Evaluation of CTan
     * @param argument an angle in radians.
     * @return result of evaluation
     */
    public double evaluateCTan(double argument) {
        return 1 / Math.tan(argument);
    }
}
