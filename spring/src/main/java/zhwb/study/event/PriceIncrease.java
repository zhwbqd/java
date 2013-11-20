package zhwb.study.event;

import org.springframework.context.ApplicationEvent;
/**
 * 涨价通知
 * @author Administrator
 *
 */
public class PriceIncrease extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public PriceIncrease(Object source) {
		super(source);
	}
	public PriceIncrease(String description) {
		super(description);
	}

}
