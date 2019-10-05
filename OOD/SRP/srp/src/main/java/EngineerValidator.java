public class EngineerValidator extends Validator {
    @Override
    public boolean checkOperation(String operation) {
        return checkOperationByRegExpr(operation, "([\\+\\-\\*/]|^sin$|^cos$|^tan$|^ctan$)", "Operation must be equal +, -, *, /, Sin, Cos, Tan, CTan");
    }
}
