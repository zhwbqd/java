package drools.study;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;

import java.util.ArrayList;

/**
 * @author jack.zhang
 * @since 2014/10/20
 */
public class TestAnd {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        KnowledgeBuilder
                kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(new ClassPathResource("test/test_and.drl"),
                ResourceType.DRL);
        KnowledgeBase knowledgeBase = kb.newKnowledgeBase();

        StatefulKnowledgeSession statefulKnowledgeSession = knowledgeBase.newStatefulKnowledgeSession();

        From f = new From();
        f.setNums(new ArrayList(){{
            add(1);
        }});
        f.setIps(null);
        statefulKnowledgeSession.insert(f);

        statefulKnowledgeSession.fireAllRules();
        statefulKnowledgeSession.dispose();
    }
}
