package expression;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.type.FactType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 *
 *
 * @author jack.zhang
 * @since 2014/10/20
 */
public class TestNull {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        KnowledgeBuilder
                kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(new ClassPathResource("expression/test_null.drl"),
                ResourceType.DRL);
        KnowledgeBase knowledgeBase = kb.newKnowledgeBase();

        StatefulKnowledgeSession statefulKnowledgeSession = knowledgeBase.newStatefulKnowledgeSession();

        FactType factType = knowledgeBase.getFactType("test", "testobj");
        Object o = factType.newInstance();
        /*不为空, fire*/
//        factType.set(o, "a", 11);
//        factType.set(o, "b", 11D);
//        factType.set(o, "c", "jack");

        /*为空*/
//        factType.set(o, "b", 11D);
//        factType.set(o, "c", "jack");

        /*为0*/
        factType.set(o, "a", 10);
        factType.set(o, "b", 0);
        factType.set(o, "c", "jack");

        statefulKnowledgeSession.insert(o);

        statefulKnowledgeSession.fireAllRules();
        statefulKnowledgeSession.dispose();
    }
}
