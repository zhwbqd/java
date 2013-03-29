package zhwb.study.juc.procus;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProCus {
	private LinkedList queue = new LinkedList();
	private int capablity = 10;
	Lock lock = new ReentrantLock();
	private final Condition full = lock.newCondition();
	private final Condition empty = lock.newCondition();

	/**
	 * @param args
	 */
	void start() {
		new Thread(new Producer()).start();
		new Thread(new Consumer()).start();
	}

	public static void main(final String[] args) {
		new ProCus().start();
	}

	class Producer implements Runnable {

		public void run() {
			while (true) {
				lock.lock();
				try {
					if (queue.size() < capablity) {
						System.out.println("Before Producer: " + queue.size());
						queue.add(new Object());
						empty.signal();
						System.out.println("After Producer: " + queue.size());
					} else {
						full.await();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}
	}

	class Consumer implements Runnable {

		public void run() {
			while (true) {
				lock.lock();
				try {
					if (queue.size() > 0) {
						System.out.println("Before Consumer: " + queue.size());
						queue.removeFirst();
						full.signal();
						System.out.println("After Consumer: " + queue.size());
					} else {
						empty.await();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}
	}
}
