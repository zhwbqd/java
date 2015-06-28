package zhwb.test.el;


import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author jack.zhang
 * @since 2015/6/28 0028
 */
public class SpEL {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        //1.给root对象赋值
        EvaluationContext context = new StandardEvaluationContext();

        //2.给自定义变量赋值
        context.setVariable("a1", "2");
        context.setVariable("a2", "2");

        Expression result3 = parser.parseExpression("#a1 eq #a2");
        System.out.println(result3.getValue(context,boolean.class));
    }
}
