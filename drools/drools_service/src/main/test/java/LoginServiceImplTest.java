import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zhwb.drools.domain.LoginService;
import zhwb.drools.domain.LoginServiceImpl;

public class LoginServiceImplTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        LoginServiceImpl loginServiceImpl = (LoginServiceImpl) context.getBean(LoginService.class);
        StatefulKnowledgeSession kstateless = (StatefulKnowledgeSession) context.getBean("ksession1");
        loginServiceImpl.checkLogin(kstateless);
        System.out.println("aa");
    }
}