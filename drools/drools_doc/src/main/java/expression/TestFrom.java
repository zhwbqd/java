package expression;

import domain.From;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 我们介绍了‘from’关键字，它允许你为模式声明一个推论的来源。
 * 这允许引擎使用不在Working Memory中的数据进行推论。
 * 源数据可能是绑定变量的子字段，或者方法调用的结果；
 * 后一种方式提供了与Hibernate集成的方法（通过调用命名的查询），Drools将把返回的结果与模式统一为一体
 *
 * Date: 2014/7/28
 * Time: 17:06
 *
 * @author jack.zhang
 */
public class TestFrom {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        KnowledgeBuilder
                kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(new ClassPathResource("expression/test_from.drl"),
                ResourceType.DRL);
        KnowledgeBase knowledgeBase = kb.newKnowledgeBase();

        StatefulKnowledgeSession statefulKnowledgeSession = knowledgeBase.newStatefulKnowledgeSession();
        From fromInstance = new From();
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        fromInstance.setIps("1,2,3,4");
        fromInstance.setNums(list);
        statefulKnowledgeSession.insert(fromInstance);

        statefulKnowledgeSession.fireAllRules();
        statefulKnowledgeSession.dispose();
    }
}
