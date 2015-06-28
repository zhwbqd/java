package zhwb.test.el;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * 相当于模板引擎
 *
 * @author jack.zhang
 * @since 2015/6/28 0028
 */
public class SpELWithTemplate {
    public static void main(String[] args) {

        ExpressionParser parser = new SpelExpressionParser();
        String template = "#{'a'}#{'b'}";
        Expression expression = parser.parseExpression(template, new TemplateParserContext());
        System.out.println(expression.getValue());

    }
}
