package com.hp.training.case1;

public class TestConsumerTask implements Runnable {

	public void run() {
		System.out.println("Consumer Thread " + Thread.currentThread().getId());

		while (!Thread.currentThread().isInterrupted()) {
			String msg = MsgQueue.instance().getNextMsg();
			if (msg != null) {
				System.out.println("receive msg:" + msg);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				break;
			}
		}

		System.out.println("Consumer Thread quit!");

	}

}
