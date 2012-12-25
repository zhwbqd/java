package com.hp.training.case4;

public class AccessThd extends Thread{
	
	@Override
	public void run()
	{
		
		long startTime = System.currentTimeMillis();
		int count = 0;
		for(int i=0; i<1000; i++)
		{
			try {
				count = Counter.instance().readCount();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				break;
			}
			
		}
		long endTime = System.currentTimeMillis();
		long duration = (endTime - startTime)/1000; 
		System.out.printf("Thread: %d, duration: %d seconds, count value: %d \n", this.getId(), duration,count);
		

		
	}

}
