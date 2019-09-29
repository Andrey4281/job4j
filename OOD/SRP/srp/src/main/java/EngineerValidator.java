public class EngineerValidator extends Validator {
    @Override
    public void checkOperation(String operation) {
        checkOperationByRegExpr(operation, "([\\+\\-\\*/]|^sin$|^cos$|^tan$|^ctan$)", "Operation must be equal +, -, *, /, Sin, Cos, Tan, CTan");
    }
}
