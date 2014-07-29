package drools.study;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.type.FactType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;

public class TestInsert {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        KnowledgeBuilder
                kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(new ClassPathResource("test/test_insert.drl"),
                ResourceType.DRL);
        KnowledgeBase knowledgeBase = kb.newKnowledgeBase();
        FactType factType = knowledgeBase.getFactType("test", "fuck");
        System.out.println(factType.getMetaData());

        factType = knowledgeBase.getFactType("test", "Customer");
        Object o = factType.newInstance();
        factType.set(o, "name", "jack");
        factType.set(o, "age", 1);

        StatefulKnowledgeSession statefulSession = knowledgeBase.newStatefulKnowledgeSession();
        statefulSession.insert(o);
        statefulSession.addEventListener(new FuckListener());

        statefulSession.fireAllRules();
        statefulSession.dispose();
        System.out.println("end.....");
    }
}