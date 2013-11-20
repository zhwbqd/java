package zhwb.study.event;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

	public static void main(String[] args) throws Exception {
		new ClassPathXmlApplicationContext("annotation-ioc-beans.xml");
		PriceDecrease decrease = new PriceDecrease("降价通知");
		PriceIncrease increase  = new PriceIncrease("涨价通知");
		EventPublisher.publishEvent(decrease);
		GlobalEventPublisher.publishApplicationEvent(increase);
	}
	
}
