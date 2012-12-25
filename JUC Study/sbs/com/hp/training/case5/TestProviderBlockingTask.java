package com.hp.training.case5;

import java.util.concurrent.ArrayBlockingQueue;

public class TestProviderBlockingTask implements Runnable{
	private ArrayBlockingQueue<String> blockingQueue;
	public TestProviderBlockingTask(ArrayBlockingQueue<String> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
	}
	public void run() {
		System.out.println("Provider Thread " + Thread.currentThread().getId());
		int i = 0;
		while(!Thread.currentThread().isInterrupted())
		{
			String msg = "msg" + i++;
			
			
			try {
				blockingQueue.put(msg);
				System.out.println("Send message:" + msg);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				break;
			}
		}
		
		System.out.println("Provider Thread quit!");
		
	}

}
