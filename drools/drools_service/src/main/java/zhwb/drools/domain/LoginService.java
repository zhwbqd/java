package zhwb.drools.domain;

import org.drools.runtime.StatefulKnowledgeSession;

/**
 * @since 7/2/14 1:52 PM.
 * @author sam.yang
 */
public interface LoginService {
    public void checkLogin(StatefulKnowledgeSession knowledgeSession);
}
