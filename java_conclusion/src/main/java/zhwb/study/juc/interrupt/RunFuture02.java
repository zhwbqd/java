package zhwb.study.juc.interrupt;

import java.util.Random;

public class RunFuture02 implements Runnable {

	private Status status;

	public RunFuture02(Status flag) {
		super();
		this.status = flag;
	}

	public void run() {
		Random rnd = new Random();
		int i = 0;
		while (i != 77777 && !Thread.currentThread().isInterrupted()) {
			i = rnd.nextInt(1000000000);
		}
		if (i == 77777) {
			System.out.println("I am " + Thread.currentThread().getName()
					+ ". I found 77777!!");
			status.setSuccess(true);
		}
	}
}