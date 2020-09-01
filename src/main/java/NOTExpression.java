public class NOTExpression extends Expression{

    private Expression rightExpression;

    public NOTExpression(Expression rightExpression) {
        this.rightExpression = rightExpression;
    }

    @Override
    public String interpreter(){
        return "!("+rightExpression.interpreter()+")";
    }
}
