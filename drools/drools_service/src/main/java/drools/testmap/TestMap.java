package drools.testmap;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2014/7/17
 * Time: 17:35
 *
 * @author jack.zhang
 */
public class TestMap {
    public static void main(String[] args) {
        KnowledgeBuilder
                kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(new ClassPathResource("test/test_map.drl"),
                ResourceType.DRL);
        KnowledgeBase knowledgeBase = kb.newKnowledgeBase();
        StatefulKnowledgeSession statefulSession = knowledgeBase.newStatefulKnowledgeSession();

        Map<String, Object> mapa = new HashMap<String, Object>();
        mapa.put("fuck", 1);
        mapa.put("fuck1", "fuck");
        statefulSession.insert(mapa);

        statefulSession.fireAllRules();
        statefulSession.dispose();


    }


}
