package zhwb.study.procus;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProCusBlockingQueue {
	private int capablity = 10;
	private BlockingQueue<Object> myList = new ArrayBlockingQueue<Object>(capablity);

	public void start() {
		new Producer().start();
		new Consumer().start();
	}

	public static void main(String[] args) throws Exception {
		ProCus0 s1 = new ProCus0();
		s1.start();
	}

	class Producer extends Thread {
		public void run() {
			while (true) {
				while (myList.size() == capablity) {
					System.out.println("warning: it's full!");
				}
				Object o = new Object();
				try {
					myList.put(o);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Producer: " + myList.size());

			}
		}
	}

	class Consumer extends Thread {
		public void run() {
			while (true) {
				try {
					while (myList.size() == 0) {
						System.out.println("warning: it's empty!");

					}
					Object o = myList.take();
					System.out.println("Consumer: " + +myList.size());

				} catch (InterruptedException ie) {
					System.out.println("consumer is interrupted!");
				}
			}
		}
	}

}
