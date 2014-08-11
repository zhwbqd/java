package drools.study;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.type.FactType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import zhwb.drools.domain.For;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2014/7/28
 * Time: 17:06
 *
 * @author jack.zhang
 */
public class TestFor {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        KnowledgeBuilder
                kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(new ClassPathResource("test/test_for.drl"),
                ResourceType.DRL);
        KnowledgeBase knowledgeBase = kb.newKnowledgeBase();

        StatefulKnowledgeSession statefulKnowledgeSession = knowledgeBase.newStatefulKnowledgeSession();
        For forInstance = new For();
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        forInstance.setIps("1,2,3,4");
        forInstance.setNums(list);
        statefulKnowledgeSession.insert(forInstance);

        statefulKnowledgeSession.fireAllRules();
        statefulKnowledgeSession.dispose();
    }
}
