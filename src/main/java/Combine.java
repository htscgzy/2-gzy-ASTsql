import java.util.LinkedList;
import java.util.Stack;

public class Combine {

    public static String getCondition(String expStr){
        Stack<String> stack_brace = new Stack<>();   //存放括号
        Stack<String> stack_spiltblock = new Stack<>();   //存放其他分割的字段包括and，or，not
        String subexpStr = "";    //缓冲区

        LinkedList<String> result = new LinkedList<>();


        for(int i=0;i<expStr.length();i++){
            if(expStr.charAt(i)==' ')
                continue;
            if(expStr.charAt(i) == '('){
                stack_brace.push("(");
                if(subexpStr != ""){
                    stack_spiltblock.push(subexpStr);
                    subexpStr = "";
                }
            }else if(expStr.charAt(i) == ')'){
                if(stack_brace.empty()){
                    throw new IllegalArgumentException();
                }
                if(subexpStr != ""){
                    stack_spiltblock.push(subexpStr);
                    subexpStr = "";
                }
                stack_brace.pop();
                String popValue = stack_spiltblock.pop();
                getExpression(popValue,result);
            }else {
                subexpStr = subexpStr+expStr.charAt(i);
            }
        }
        if(!stack_spiltblock.empty())
            getExpression(stack_spiltblock.pop(),result);
        if(!stack_brace.empty())
            throw new IllegalArgumentException();
        return result.get(0);    //拼接在getExpression里做
    }



    //根据值创建合适的表达式对象
    private static void getExpression(String popValue, LinkedList<String> result) {

        if(popValue.equalsIgnoreCase("NOT")){
            Expression varExpression = new ExpressionVar(result.get(result.size()-1));
            Expression notExpression = new NOTExpression(varExpression);
            result.remove(result.size()-1);
            result.add(notExpression.interpreter());
        }else if(popValue.equalsIgnoreCase("AND")){
            Expression varrightExpression = new ExpressionVar(result.get(result.size()-1));
            Expression varleftExpression = new ExpressionVar(result.get(result.size()-2));
            Expression andExpression = new ANDExpression(varleftExpression,varrightExpression);
            result.remove(result.size()-1);
            result.remove(result.size()-1);
            result.add(andExpression.interpreter());
        }else if(popValue.equalsIgnoreCase("OR")){
            Expression varrightExpression = new ExpressionVar(result.get(result.size()-1));
            Expression varleftExpression = new ExpressionVar(result.get(result.size()-2));
            Expression orExpression = new ORExpression(varleftExpression,varrightExpression);
            result.remove(result.size()-1);
            result.remove(result.size()-1);
            result.add(orExpression.interpreter());
        }else
            result.add(popValue);

    }
}
