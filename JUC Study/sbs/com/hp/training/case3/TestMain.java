package com.hp.training.case3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestMain {
	public static void main(String[] args)
	{
		Thread consumer = new Thread(new TestConsumerBlockingLockTask());
		consumer.start();
		
		Thread provider = new Thread(new TestProviderBlockingLockTask());
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
