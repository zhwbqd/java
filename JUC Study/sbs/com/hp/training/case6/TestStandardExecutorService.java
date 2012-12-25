package com.hp.training.case6;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestStandardExecutorService {
	
	public static void main(String[] args)
	{
		//5 core threads 
		//10 maximum threads
		//wait 2 seconds before terminating the thread
		ThreadPoolExecutor executorPool = new ThreadPoolExecutor(5,10, 2, TimeUnit.SECONDS, new ArrayBlockingQueue(2));
		int i=0;
		while(i<7)
		{
			ExecuteTask task = new ExecuteTask(i++);
			executorPool.execute(task);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			} 
		}
	}

}
