package drools.mark;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;

/**
 * RuleBaseFacatory 单实例RuleBase生成工具
 *
 * @author quzishen
 */
public class KnowledgeBaseFacatory {
    private static KnowledgeBase knowledgeBase;

    public synchronized static KnowledgeBase getKnowledgeBase() {
        if (knowledgeBase == null) {
            KnowledgeBaseConfiguration kbConf = KnowledgeBaseFactory
                    .newKnowledgeBaseConfiguration();
            kbConf.setProperty("org.drools.sequential", "true");
            knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase(kbConf);
        }
        return knowledgeBase;
    }
}