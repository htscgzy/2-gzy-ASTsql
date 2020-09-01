public class ORExpression extends Expression {
    private Expression leftExpression;
    private Expression rightExpression;


    public ORExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public String interpreter(){
        return "("+leftExpression.interpreter()+") or ("+rightExpression.interpreter()+")";

    }
}
