public class ExpressionVar extends Expression{

    private String conditionStr;

    public ExpressionVar(String conditionStr) {
        this.conditionStr = conditionStr;
    }

    @Override
    public String interpreter(){
        return conditionStr;

    }
}
