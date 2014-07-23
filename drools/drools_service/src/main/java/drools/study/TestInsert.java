package drools.study;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.concurrent.Command;
import org.drools.definition.type.FactType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.StatelessKnowledgeSession;
import org.drools.runtime.rule.QueryResults;

public class TestInsert {
    public static void main(String[] args) {
        KnowledgeBuilder
                kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(new ClassPathResource("test/test_insert.drl"),
                ResourceType.DRL);
        KnowledgeBase knowledgeBase = kb.newKnowledgeBase();
        FactType factType = knowledgeBase.getFactType("test", "fuck");
        System.out.println(factType.getMetaData());
        StatefulKnowledgeSession statefulSession = knowledgeBase.newStatefulKnowledgeSession();
        statefulSession.fireAllRules();
        QueryResults qr = statefulSession.getQueryResults("query fact count");
        statefulSession.dispose();
        System.out.println("customer 对象数目:" + qr.size());
        System.out.println("end.....");
    }
}