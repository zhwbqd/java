package com.hp.training.case5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;

public class TestRunnableMain {
	public static void main(String[] args)
	{
		//fixed blocking queue
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue(5);
		Thread consumer = new Thread(new TestConsumerBlockingTask(queue));
		consumer.start();
		
		Thread provider = new Thread(new TestProviderBlockingTask(queue));
		provider.start();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			String anyLine = reader.readLine();
			
			//exit the thread now 
			consumer.interrupt();
			provider.interrupt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
