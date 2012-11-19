
package zhwb.observerPattern;

import java.util.Observable;
import java.util.Observer;

public class HappyObserver implements Observer
{

	public void update(Observable o, Object arg) {
		System.out.printf("Observer count is %d \n", o.countObservers());
        System.out.printf("Observerable say that %s \n", arg);
	}

}
