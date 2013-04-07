package zhwb.study.juc;

import java.util.Random;

public class RunInOrderTest {

	public static void main(String[] args) throws InterruptedException {

		Thread a = new Printer("A");
		Thread b = new Printer("B");
		Thread c = new Printer("C");
		a.start();
		a.join();

		b.start();
		b.join();

		c.start();

	}

}

class Printer extends Thread {
	private String field;

	public Printer(String field) {
		super();
		this.field = field;
	}

	@Override
	public void run() {
		try {
			sleep(new Random().nextInt(1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("I am " + field);
	}
}
