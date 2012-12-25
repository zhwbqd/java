package com.hp.training.case4;

public class WriteThd extends Thread{
	public void run()
	{
		while(true)
		{
			try {
				Counter.instance().incrementCount();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				break;
			}
		}
	}

}
