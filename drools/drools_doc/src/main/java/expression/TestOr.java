package expression;

import domain.Employee;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * OR 实际上并不是按照||的短路逻辑进行处理的
 * 而是将一个规则写为两个子规则, 分别匹配和执行
 *
 *
 * @author jack.zhang
 * @since 2014/10/21
 */
public class TestOr {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        KnowledgeBuilder
                kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(new ClassPathResource("expression/test_or.drl"),
                ResourceType.DRL);
        KnowledgeBase knowledgeBase = kb.newKnowledgeBase();

        StatefulKnowledgeSession statefulKnowledgeSession = knowledgeBase.newStatefulKnowledgeSession();


        Employee e1 = new Employee();
        statefulKnowledgeSession.insert(e1);

        Employee e2 = new Employee();
        e2.setAge(66);
        e2.setSex("m");
        e2.setName("Jack");
        statefulKnowledgeSession.insert(e2);

        Employee e3 = new Employee();
        e3.setAge(61);
        e3.setSex("f");
        e3.setName("Lucy");
        statefulKnowledgeSession.insert(e3);

        statefulKnowledgeSession.fireAllRules();
        statefulKnowledgeSession.dispose();
    }
}
