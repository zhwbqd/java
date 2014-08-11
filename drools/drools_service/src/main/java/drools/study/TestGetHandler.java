package drools.study;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.common.DefaultFactHandle;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import zhwb.drools.domain.Vip;

public class TestGetHandler {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        KnowledgeBuilder
                kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(new ClassPathResource("test/test_getHandler.drl"),
                ResourceType.DRL);
        KnowledgeBase knowledgeBase = kb.newKnowledgeBase();

        StatefulKnowledgeSession statefulSession = knowledgeBase.newStatefulKnowledgeSession();
        statefulSession.fireAllRules();
        FactHandle fhl = null;
        for (FactHandle factHandle : statefulSession.getFactHandles()) {
            if(((DefaultFactHandle)(factHandle)).getObject() instanceof Vip){
                fhl = factHandle;
            }
        }
        Vip vip = (Vip) statefulSession.getObject(fhl);
        System.out.println(vip.getName());
        statefulSession.dispose();
        System.out.println("end.....");
    }
}