package zhwb.study.procus;

import java.util.LinkedList;

public class ProCus0 {
	private LinkedList<Object> myList = new LinkedList<Object>();

	public ProCus0() {
	}

	public void start() {
		new Producer(myList).start();
		new Consumer(myList).start();
	}

	public static void main(String[] args) throws Exception {
		ProCus0 s1 = new ProCus0();
		s1.start();
	}
}

class Producer extends Thread {
	private LinkedList<Object> myList = null;
	private int capabilty = 10;

	public Producer(LinkedList<Object> myList) {
		this.myList = myList;
	}

	public void run() {
		while (true) {
			synchronized (myList) {
				try {
					while (myList.size() == capabilty) {
						System.out.println("warning: it's full!");
						myList.wait();
					}
					Object o = new Object();
					if (myList.add(o)) {
						System.out.println("Producer: " + myList.size());
						myList.notify();
					}
				} catch (InterruptedException ie) {
					System.out.println("producer is interrupted!");
				}
			}
		}
	}
}

class Consumer extends Thread {
	private LinkedList<Object> myList = null;

	public Consumer(LinkedList<Object> myList) {
		this.myList = myList;
	}

	public void run() {
		while (true) {
			synchronized (myList) {
				try {
					while (myList.size() == 0) {
						System.out.println("warning: it's empty!");
						myList.wait();
					}
					Object o = myList.removeLast();
					System.out.println("Consumer: " + +myList.size());
					myList.notify();
				} catch (InterruptedException ie) {
					System.out.println("consumer is interrupted!");
				}
			}
		}
	}
}
