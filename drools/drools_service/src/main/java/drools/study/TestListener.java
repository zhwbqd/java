package drools.study;

import com.alibaba.fastjson.JSON;
import org.drools.KnowledgeBase;
import org.drools.base.SalienceInteger;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.definition.rule.Rule;
import org.drools.definition.type.FactType;
import org.drools.definitions.rule.impl.RuleImpl;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;

import java.util.Collection;

/**
 * @author sam.yang
 * @since 7/28/14 4:51 PM.
 */
public class TestListener {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        KnowledgeBuilder
                kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(new ClassPathResource("test/test_listener.drl"),
                ResourceType.DRL);
        KnowledgeBase knowledgeBase = kb.newKnowledgeBase();
        FactType factType = knowledgeBase.getFactType("test", "Customer");
        Object o = factType.newInstance();
        factType.set(o, "name", "zhangsan");
        Object o1 = factType.newInstance();
        factType.set(o1, "name", "zhangsan");

        StatefulKnowledgeSession statefulSession = knowledgeBase.newStatefulKnowledgeSession();
        statefulSession.addEventListener((new TraceListener()));
        statefulSession.insert(o);
        statefulSession.insert(o1);
        statefulSession.fireAllRules(1);
        System.out.println(getAllRules(statefulSession.getKnowledgeBase()));
        statefulSession.dispose();
        System.out.println("end.....");
    }
    private static Collection<Rule> getAllRules(KnowledgeBase knowledgeBase) {
        for (KnowledgePackage knowledgePackage : knowledgeBase.getKnowledgePackages()) {
            if (knowledgePackage.getRules().size() == 0) {
                continue;
            }
            for (Rule rule : knowledgePackage.getRules()) {

                System.out.println("-------------------" + JSON.toJSONString(((SalienceInteger)(((RuleImpl)rule).getRule().getSalience())).toString()));
            }
            return knowledgePackage.getRules();
        }
        return null;
    }
}
