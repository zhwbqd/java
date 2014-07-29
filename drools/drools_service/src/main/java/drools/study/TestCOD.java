package drools.study;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.type.FactType;
import org.drools.event.rule.DebugAgendaEventListener;
import org.drools.event.rule.DebugWorkingMemoryEventListener;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;

/**
 * Date: 2014/7/28
 * Time: 17:06
 *
 * @author jack.zhang
 */
public class TestCOD {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        KnowledgeBuilder
                kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(new ClassPathResource("test/cod.drl"),
                ResourceType.DRL);
        KnowledgeBase knowledgeBase = kb.newKnowledgeBase();

        StatefulKnowledgeSession statefulKnowledgeSession = knowledgeBase.newStatefulKnowledgeSession();
        statefulKnowledgeSession.insert(getData(knowledgeBase));
        statefulKnowledgeSession.addEventListener(new FuckListener());

        FactType factType = knowledgeBase.getFactType("test", "RuleExecResult");
        Object data = factType.newInstance();
        FactHandle resultHandler = statefulKnowledgeSession.insert(data);
        statefulKnowledgeSession.fireAllRules(1);

        Object object = statefulKnowledgeSession.getObject(resultHandler);

        System.out.println(factType.get(object, "level") + ":" + factType.get(object, "reason"));

        statefulKnowledgeSession.dispose();
    }

    private static Object getData(KnowledgeBase knowledgeBase) throws InstantiationException, IllegalAccessException {
        FactType factType = knowledgeBase.getFactType("test", "cod");
        Object data = factType.newInstance();
        factType.set(data, "is_black", false);
        factType.set(data, "uid", 6);
        factType.set(data, "cid", 6);
        factType.set(data, "contactPhone", 6);
        return data;
    }

}
