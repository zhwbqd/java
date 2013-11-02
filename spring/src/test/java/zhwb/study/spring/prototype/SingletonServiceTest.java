package zhwb.study.spring.prototype;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SingletonServiceTest {

  @Test
  public void invokeProto() {
    ApplicationContext context = new ClassPathXmlApplicationContext("annotation-ioc-beans.xml");
    SingletonService sig = (SingletonService) context.getBean("singletonService");
    
    sig.invokeProto();
    
    sig.invokeProto();
  }
}
