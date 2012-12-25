package com.hp.training.case4;

public class TestMain {
	public static void main(String[] args)
	{
		
		AccessThd[] accessThds = new AccessThd[10];
		
		for(int i=0; i<10; i++)
		{
			accessThds[i] = new AccessThd();
			accessThds[i].start();
		}
		
		WriteThd writeThd = new WriteThd();
		writeThd.start();
		
	}
	
}
