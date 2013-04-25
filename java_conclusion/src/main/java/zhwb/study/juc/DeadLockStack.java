package zhwb.study.juc;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadLockStack {

	LinkedList list = new LinkedList();

	public synchronized void push(Object x) {
		synchronized (list) {
			list.addLast(x);
			notify();
		}
	}

	public synchronized Object pop() throws Exception {
		synchronized (list) {
			if (list.isEmpty()) {
				wait();
			}

			return list.removeFirst();
		}
	}

	public static void main(String[] args) {
		final DeadLockStack s = new DeadLockStack();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Runnable() {
			public void run() {
				try {
					System.out.println(s.pop());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		exec.execute(new Runnable() {
			public void run() {
				s.push("test");
			}
		});
		exec.shutdown();
	}

}
