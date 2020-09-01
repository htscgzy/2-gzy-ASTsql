public class ANDExpression extends Expression {
    private Expression leftExpression;
    private Expression rightExpression;



    public ANDExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public String interpreter(){
        return "("+leftExpression.interpreter()+") and ("+rightExpression.interpreter()+")";

    }
}
