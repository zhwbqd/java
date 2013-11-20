package zhwb.study.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
/**
 * 价格监听器
 * @author Administrator
 *
 */
@Component
public class PriceListener implements ApplicationListener {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof PriceDecrease){
			System.out.println("PriceListener监听到PriceDecrease事件："+event.getSource());
		}else if(event instanceof PriceIncrease){
			System.out.println("PriceListener监听到PriceIncrease事件："+event.getSource());
		}
	}

}
