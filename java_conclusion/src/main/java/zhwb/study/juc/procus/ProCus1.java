package zhwb.study.juc.procus;

import java.util.LinkedList;

public class ProCus1 {
	private LinkedList queue = new LinkedList();
	private int capablity = 10;

	/**
	 * @param args
	 */
	void start() {
		new Thread(new Producer()).start();
		new Thread(new Producer()).start();
		new Thread(new Consumer()).start();
		new Thread(new Consumer()).start();
		new Thread(new Consumer()).start();
		new Thread(new Producer()).start();
	}

	public static void main(final String[] args) {
		new ProCus1().start();
	}

	class Producer implements Runnable {

		public void run() {
			while (true) {
				synchronized (queue) {
					if (queue.size() < capablity) {
						queue.add(new Object());
						queue.notify();
						System.out.println("After Producer: " + queue.size());
					} else {
						try {
                            System.out.println("warning: it's full!");
							queue.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}

	}

	class Consumer implements Runnable {

		public void run() {
			while (true) {
				synchronized (queue) {
					if (queue.size() > 0) {
						queue.removeFirst();
						queue.notify();
						System.out.println("After Consumer: " + queue.size());
					} else {
						try {
                            System.out.println("warning: it's empty!");
							queue.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
