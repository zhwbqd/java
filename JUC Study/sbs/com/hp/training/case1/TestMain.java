package com.hp.training.case1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class TestMain {
	
	public static void main(String[] args)
	{
		TestConsumerThd consumer = new TestConsumerThd();
		consumer.start();
		
		TestProviderThd provider = new TestProviderThd();
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
