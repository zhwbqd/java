package zhwb.study.spring.prototype;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS,value=BeanDefinition.SCOPE_PROTOTYPE)
public class PrototypeService {

    public void hello() {
        System.out.println("HELLO");
    }
}
