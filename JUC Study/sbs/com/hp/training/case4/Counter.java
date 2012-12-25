package com.hp.training.case4;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Counter {
	
	private static Counter counter = new Counter();
	
	private Counter(){}
	
	public static Counter instance() {
		return counter;
	}
	
	private int count=0; 
	
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	public int readCount() throws InterruptedException
	{
		readWriteLock.writeLock().lock();
		try
		{
			Thread.sleep(10);
			return count;
		}
		finally {
			readWriteLock.writeLock().unlock();
		}
		
	}
	
	public void incrementCount() throws InterruptedException
	{
		readWriteLock.writeLock().lock();
		try{
			Thread.sleep(10);
			count++;
			
		}
		finally{
			readWriteLock.writeLock().unlock();
		}
	}
	
	

}
