package zhwb.study.event;

import org.springframework.context.ApplicationEvent;
/**
 * 降价通知
 * @author Administrator
 *
 */
public class PriceDecrease extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public PriceDecrease(Object source) {
		super(source);
	}
	public PriceDecrease(String description) {
		super(description);
	}

}
