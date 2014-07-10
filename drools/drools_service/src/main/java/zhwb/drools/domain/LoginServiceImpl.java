package zhwb.drools.domain;

import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.stereotype.Service;

/**
 * @author sam.yang
 * @since 7/2/14 1:55 PM.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public void checkLogin(StatefulKnowledgeSession knowledgeSession) {
        System.out.println("s");
        Vip vip = new Vip();
        knowledgeSession.insert(vip);
        knowledgeSession.fireAllRules();
        knowledgeSession.dispose();
        System.out.println("e");
    }
    public static boolean checkDB(String name,String password){
        //实际可以到数据库匹配
        return name.trim().equals("jack")&&password.trim().equals("123");
    }
}
