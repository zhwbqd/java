package expression;

import domain.Employee;
import domain.Employer;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jack.zhang
 * @since 2014/10/22
 */
public class TestForAll {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        KnowledgeBuilder
                kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(new ClassPathResource("expression/test_forall.drl"),
                ResourceType.DRL);
        KnowledgeBase knowledgeBase = kb.newKnowledgeBase();

        StatefulKnowledgeSession statefulKnowledgeSession = knowledgeBase.newStatefulKnowledgeSession();


        Employee e1 = new Employee();
        statefulKnowledgeSession.insert(e1);

        List<Employee> employeeList = new ArrayList<Employee>();

        Employee e2 = new Employee();
        e2.setAge(66);
        e2.setSex("m");
        e2.setName("Jack");
        statefulKnowledgeSession.insert(e2);


        Employee e3 = new Employee();
        e3.setAge(66);
        e3.setSex("m");
        e3.setName("Lucy");
        statefulKnowledgeSession.insert(e3);

        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);

        Employer employer = new Employer();
        employer.setEmployees(employeeList);

        statefulKnowledgeSession.insert(employer);

        statefulKnowledgeSession.fireAllRules();
        statefulKnowledgeSession.dispose();
    }
}
