package com.hp.training.case2;

public class TestProviderBlockingTask implements Runnable{

	public void run() {
		System.out.println("Provider Thread " + Thread.currentThread().getId());
		int i = 0;
		while(!Thread.currentThread().isInterrupted())
		{
			String msg = "msg" + i++;
			BlockingMsgQueue.instance().pushMsg(msg);
			System.out.println("Send message:" + msg);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				break;
			}
		}
		
		System.out.println("Provider Thread quit!");
		
	}

}
