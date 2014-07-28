package drools.study;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;

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
        FactHandle handler = statefulSession.insert(new Fuck());
        Fuck object = (Fuck) statefulSession.getObject(handler);
        System.out.println(object.fucked);
        statefulSession.fireAllRules();
        System.out.println(object.fucked);
        statefulSession.dispose();


    }

    public static class Fuck{
        private boolean fucked;

        public boolean isFucked() {
            return fucked;
        }

        public void setFucked(boolean fucked) {
            this.fucked = fucked;
        }

        @Override
        public String toString() {
            return "Fuck{" +
                    "fucked=" + fucked +
                    '}';
        }
    }

}
