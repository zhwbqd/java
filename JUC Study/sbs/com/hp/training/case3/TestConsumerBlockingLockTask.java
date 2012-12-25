package com.hp.training.case3;


public class TestConsumerBlockingLockTask implements Runnable {

	public void run() {
		System.out.println("Consumer Thread " + Thread.currentThread().getId());

		while (!Thread.currentThread().isInterrupted()) {
			try {
				String msg = BlockingLockMsgQueue.instance().getNextMsg();

				System.out.println("receive msg:" + msg);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				break;
			}
		}

		System.out.println("Consumer Thread quit!");

	}

}
