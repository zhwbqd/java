package expression;

import domain.Address;
import domain.Person;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;

/**
 * @author jack.zhang
 * @since 2014/10/24
 */
public class TestFromAdv {

    public static void main(String[] args) {
        KnowledgeBuilder
                kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(new ClassPathResource("expression/test_from_adv.drl"),
                ResourceType.DRL);
        KnowledgeBase knowledgeBase = kb.newKnowledgeBase();

        StatefulKnowledgeSession statefulKnowledgeSession = knowledgeBase.newStatefulKnowledgeSession();

        Person person = new Person();

        Address address = new Address();
        address.setCity("Qingdao");
        address.setState("ShanDong");
        person.setAddress(address);

        FactHandle personHandle = statefulKnowledgeSession.insert(person);


        statefulKnowledgeSession.fireAllRules();

        Person object = (Person) statefulKnowledgeSession.getObject(personHandle);
        System.out.println(object.getDiscount());
        System.out.println(object.getSaleRegion());
        statefulKnowledgeSession.dispose();
    }

}
