package com.hp.training.case1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestRunnableMain {
	public static void main(String[] args)
	{
		Thread consumer = new Thread(new TestConsumerTask());
		consumer.start();
		
		Thread provider = new Thread(new TestProviderTask());
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
