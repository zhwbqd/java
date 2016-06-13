package expression;

import domain.Employer;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatelessKnowledgeSession;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author jack.zhang
 * @since 2014/10/20
 */
public class TestAnd {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        KnowledgeBuilder
                kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(new ClassPathResource("expression/test_and.drl"),
                ResourceType.DRL);
        KnowledgeBase knowledgeBase = kb.newKnowledgeBase();

        StatelessKnowledgeSession statefulKnowledgeSession = knowledgeBase.newStatelessKnowledgeSession();

//        From f = new From();
        Employer employer = new Employer();
//        Employer employer1 = new Employer();
//        f.setNums(new ArrayList(){{
//            add(1);
//        }});
//        f.setIps("1,2,3,4");
//        statefulKnowledgeSession.insert(f);
        List<Object> list = new ArrayList<Object>();
        list.add(employer);
//        list.add(employer1);

        statefulKnowledgeSession.execute(list);
    }
}
