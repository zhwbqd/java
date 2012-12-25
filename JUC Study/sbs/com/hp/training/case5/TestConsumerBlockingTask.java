package com.hp.training.case5;

import java.util.concurrent.ArrayBlockingQueue;

public class TestConsumerBlockingTask implements Runnable {
	
	private ArrayBlockingQueue<String> blockingQueue;
	public TestConsumerBlockingTask(ArrayBlockingQueue<String> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
	}

	public void run() {
		System.out.println("Consumer Thread " + Thread.currentThread().getId());

		while (!Thread.currentThread().isInterrupted()) {
			try {
				String msg = blockingQueue.take();

				System.out.println("receive msg:" + msg);
				Thread.sleep(3000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				break;
			}
		}

		System.out.println("Consumer Thread quit!");

	}

}
