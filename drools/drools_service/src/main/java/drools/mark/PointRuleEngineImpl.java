package drools.mark;

import org.drools.KnowledgeBase;
import org.drools.builder.*;
import org.drools.compiler.PackageBuilder;
import org.drools.definition.KnowledgePackage;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.AgendaFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * 规则接口实现类
 */
public class PointRuleEngineImpl implements PointRuleEngine {

    private KnowledgeBase knowledgeBase;

    /* (non-Javadoc)
     * @see com.drools.demo.point.PointRuleEngine#initEngine()
     */
    public void initEngine() {
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
        KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(new ClassPathResource("mark/mark.drl"), ResourceType.DRL);
        kb.add(new ClassPathResource("mark/submark.drl"), ResourceType.DRL);

        KnowledgeBuilderErrors kbErrors = kb.getErrors();
        if (kbErrors.size() > 0) {
            for (KnowledgeBuilderError kbError : kbErrors) {
                System.out.println("parse error:" + kbError.getMessage());
            }
            throw new IllegalArgumentException("can not parse knowledge." + kbErrors.toString());
        }

        knowledgeBase = KnowledgeBaseFacatory.getKnowledgeBase();
        knowledgeBase.addKnowledgePackages(kb.getKnowledgePackages());
    }

    /* (non-Javadoc)
     * @see com.drools.demo.point.PointRuleEngine#refreshEnginRule()
     */
    public void refreshEnginRule() {
        for (KnowledgePackage knowledgePackage : knowledgeBase.getKnowledgePackages()) {
            knowledgeBase.removeKnowledgePackage(knowledgePackage.getName());
        }
        initEngine();
    }

    /* (non-Javadoc)
     * @see com.drools.demo.point.PointRuleEngine#executeRuleEngine(com.drools.demo.point.PointDomain)
     */
    public void executeRuleEngine(final PointDomain pointDomain) {
        if (null == knowledgeBase.getKnowledgePackages() || 0 == knowledgeBase.getKnowledgePackages().size()) {
            throw new IllegalStateException("need to init first");
        }

        StatefulKnowledgeSession statefulSession = knowledgeBase.newStatefulKnowledgeSession();
        statefulSession.insert(pointDomain);

        // fire
        statefulSession.fireAllRules(new AgendaFilter() {
            @Override
            public boolean accept(org.drools.runtime.rule.Activation activation) {
                return !activation.getRule().getName().contains("_test");
            }
        });

        statefulSession.dispose();
    }

    /**
     * 从Drl规则文件中读取规则
     *
     * @return
     * @throws Exception
     */
    private PackageBuilder getPackageBuilderFromDrlFile() throws Exception {
        // 获取测试脚本文件
        List<String> drlFilePath = getTestDrlFile();
        // 装载测试脚本文件
        List<Reader> readers = readRuleFromDrlFile(drlFilePath);

        PackageBuilder backageBuilder = new PackageBuilder();
        for (Reader r : readers) {
            backageBuilder.addPackageFromDrl(r);
        }

        // 检查脚本是否有问题
        if (backageBuilder.hasErrors()) {
            throw new Exception(backageBuilder.getErrors().toString());
        }

        return backageBuilder;
    }

    /**
     * @param drlFilePath 脚本文件路径
     * @return
     * @throws FileNotFoundException
     */
    private List<Reader> readRuleFromDrlFile(List<String> drlFilePath) throws FileNotFoundException {
        if (null == drlFilePath || 0 == drlFilePath.size()) {
            return null;
        }

        List<Reader> readers = new ArrayList<Reader>();

        for (String ruleFilePath : drlFilePath) {
            readers.add(new FileReader(new File(ruleFilePath)));
        }

        return readers;
    }

    /**
     * 获取测试规则文件
     *
     * @return
     */
    private List<String> getTestDrlFile() {
        List<String> drlFilePath = new ArrayList<String>();
        drlFilePath
                .add("mark/mark.drl");
        drlFilePath
                .add("mark/submark.drl");

        return drlFilePath;
    }
}