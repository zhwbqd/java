package zhwb.study.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
@Component
public class EventPublisher implements ApplicationEventPublisherAware {

	private static ApplicationEventPublisher eventPublisher;
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
		System.out.println("注入属性：ApplicationEventPublisher");
		EventPublisher.eventPublisher = eventPublisher;
	}

	public static void publishEvent(ApplicationEvent  event){
		System.out.println("EventPublisher发布了一个事件");
		eventPublisher.publishEvent(event);
	}
	
}
