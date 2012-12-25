package com.hp.training.case1;

public class TestConsumerThd extends Thread {

	public void run() {
		System.out.println("Consumer Thread " + Thread.currentThread().getId());
		System.out.println("Consumer Thread " + this.getId());

		while (!this.isInterrupted()) {
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
