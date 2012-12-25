package com.hp.training.case6;

public class ExecuteTask implements Runnable{

	private int taskID;
	
	public ExecuteTask(int taskID)
	{
		this.taskID = taskID;
	}
	
	public void run() {
		//sleep for long time. 
		System.out.println("Thread:" + Thread.currentThread().getId() + " is now executing task:" + taskID);
		try {
			Thread.sleep(15000);
			System.out.println("Thread:" + Thread.currentThread().getId() + " quit task:" + taskID);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Thread is terminated, exit..");
		} 
		
	}
	

}
