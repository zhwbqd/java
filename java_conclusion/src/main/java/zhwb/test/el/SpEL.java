package zhwb.test.el;


import org.apache.commons.lang3.time.DateUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Date;

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
        Date now = new Date();
        Date before = DateUtils.addHours(now, -1);
        Date after = DateUtils.addHours(now, 1);
        context.setVariable("a1", now);
        context.setVariable("a2", before);
        context.setVariable("a3", after);
        context.setVariable("a4", false);

        Expression result3 = parser.parseExpression("#a1 > #a2");
        Expression result4 = parser.parseExpression("#a1 < #a3");
        Expression result5 = parser.parseExpression("#a4 == false");
        Expression result6 = parser.parseExpression("T(org.apache.commons.lang3.time.DateUtils).isSameDay(new java.util.Date(),new java.util.Date())");
        Expression result7 = parser.parseExpression("T(org.apache.commons.lang3.time.DateUtils).isSameDay(#a1,#a2)");
        int result1 = parser.parseExpression("#a1.Year").getValue(context, int.class);
        System.out.println(result3.getValue(context,boolean.class));
        System.out.println(result4.getValue(context,boolean.class));
        System.out.println(result5.getValue(context,boolean.class));
        System.out.println(result1);
        System.out.println(result6.getValue(context,boolean.class));
        System.out.println(result7.getValue(context,boolean.class));
        System.out.println(result7.getValue(context,boolean.class));
    }
}
