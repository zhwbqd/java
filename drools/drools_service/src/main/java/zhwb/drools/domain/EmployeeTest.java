package zhwb.drools.domain;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.QueryResults;
import org.drools.runtime.rule.QueryResultsRow;

public class EmployeeTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(ResourceFactory.newClassPathResource("zhwb/employeeSample.drl"), ResourceType.DRL);

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kb.getKnowledgePackages());
        StatefulKnowledgeSession kSession = kbase.newStatefulKnowledgeSession();

        Employee e = new Employee();
        e.setAge(20);
        e.setService(5);
        e.setName("Tom");
        Employee e1 = new Employee();
        e1.setAge(40);
        e1.setService(21);
        e1.setName("John");
        Employee e2 = new Employee();
        e2.setAge(40);
        e2.setService(20);
        e2.setName("James");

        Employer manager = new Employer(40, 30);
        manager.setName("Li");
//		manager.addEmployee(e1);
//		manager.addEmployee(e2);

        Employer manager1 = new Employer(45, 32);
        manager1.setName("xu");
        manager1.addEmployee(e1);

        kSession.insert(e);
        kSession.insert(e1);
        kSession.insert(e2);
        kSession.insert(manager);
//		kSession.insert(manager1);

        kSession.fireAllRules();

        KnowledgeBuilderErrors kbErrors = kb.getErrors();
        if (kbErrors.size() > 0) {
            for (KnowledgeBuilderError kbError : kbErrors) {
                System.out.println("parse error:" + kbError.getMessage());
            }
            throw new IllegalArgumentException("can not parse knowledge.");
        }

        QueryResults qrs = kSession.getQueryResults("query employee days");
        for (QueryResultsRow qrr : qrs) {
            Employee etmp = (Employee) qrr.get("e");
            System.out.println("vacation days(age>18): " + etmp.getDays());
        }
        QueryResults qrs2 = kSession.getQueryResults("manager");
        for (QueryResultsRow qrr : qrs2) {
            Employer mtmp = (Employer) qrr.get("m");
            System.out.println("employee count of manager: " + mtmp.count() + ", manager name :" + mtmp.getName() + " ");
            for (Employee ee : mtmp.getEmployees()) {
                System.out.println(ee.getName() + ",");
            }
        }
        kSession.dispose();

    }

}

