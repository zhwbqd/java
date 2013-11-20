package zhwb.study.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;
@Component
public class GlobalEventPublisher implements ApplicationContextAware {

	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		GlobalEventPublisher.context = context;
	}

	public static void publishApplicationEvent(ApplicationEvent event){
		System.out.println("通过GlobalEventPublisher发布了一个事件");
		context.publishEvent(event);
	}
	
}
