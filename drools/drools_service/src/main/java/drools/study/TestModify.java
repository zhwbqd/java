package drools.study;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;

public class TestModify {
    public static void main(String[] args) {
        KnowledgeBuilder
                kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(new ClassPathResource("test/test_modify.drl"),
                ResourceType.DRL);
        KnowledgeBase knowledgeBase = kb.newKnowledgeBase();
        StatefulKnowledgeSession
                statefulSession = knowledgeBase.newStatefulKnowledgeSession();
        Customer cus = new Customer();
        cus.setAge(20);
        cus.setId("ZhangeShan");
        cus.setName("张三");
        statefulSession.insert(cus);
        statefulSession.fireAllRules();
        statefulSession.dispose();
        System.out.println("end.....");
    }
}