package com.hp.training.case1;

public class TestProviderThd extends Thread {

	public void run() {
		System.out.println("Provider Thread " + Thread.currentThread().getId());
		System.out.println("Provider Thread " + this.getId());
		int i = 0;
		while (!this.isInterrupted()) {
			String msg = "msg" + i++;
			MsgQueue.instance().pushMsg(msg);
			System.out.println("Send message:" + msg);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				break;
			}
		}

		System.out.println("Provider Thread quit!");
	}

}
