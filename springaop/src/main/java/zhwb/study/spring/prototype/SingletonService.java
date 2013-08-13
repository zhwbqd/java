package zhwb.study.spring.prototype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingletonService {

    @Autowired
    PrototypeService proto;
    
    public void invokeProto(){
        System.out.println(proto.toString());
        proto.hello();
    }
}
