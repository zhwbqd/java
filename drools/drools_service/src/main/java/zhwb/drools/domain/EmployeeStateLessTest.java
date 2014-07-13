package zhwb.drools.domain;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.*;
import org.drools.command.CommandFactory;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.StatelessKnowledgeSession;
import org.drools.runtime.rule.QueryResults;
import org.drools.runtime.rule.QueryResultsRow;

import java.util.ArrayList;
import java.util.List;

public class EmployeeStateLessTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(ResourceFactory.newClassPathResource("zhwb/employeeSample.drl"), ResourceType.DRL);

        KnowledgeBuilderErrors kbErrors = kb.getErrors();
        if (kbErrors.size() > 0) {
            for (KnowledgeBuilderError kbError : kbErrors) {
                System.out.println("parse error:" + kbError.getMessage());
            }
            throw new IllegalArgumentException("can not parse knowledge.");
        }

        /*KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kb.getKnowledgePackages());*/

        KnowledgeBaseConfiguration kbConf = KnowledgeBaseFactory
                .newKnowledgeBaseConfiguration();
        kbConf.setProperty("org.drools.sequential", "true");
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(kbConf);
        kbase.addKnowledgePackages(kb.getKnowledgePackages());
//        KnowledgeBase kbase = kb.newKnowledgeBase(); //无法设置knowledgeBaseConf

        StatelessKnowledgeSession kSession = kbase.newStatelessKnowledgeSession();

        List<Object> list = new ArrayList<Object>();
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

        list.add(e);
        list.add(e1);
        list.add(e2);
        list.add(manager);

        kSession.execute(CommandFactory.newInsert(list));
    }
}

