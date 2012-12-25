package com.hp.training.case3;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.hp.training.case2.BlockingMsgQueue;

public class BlockingLockMsgQueue {
	
	
private static BlockingLockMsgQueue msgQueue = new BlockingLockMsgQueue();
	
	private LinkedList<String> msgList = new LinkedList();
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
	
	private BlockingLockMsgQueue(){};
	
	public static synchronized BlockingLockMsgQueue instance()
	{
		return msgQueue;
	}
	
	public String getNextMsg() throws InterruptedException
	{
		lock.lock();
		try
		{
			
			while(msgList.size() == 0)
			{
				cond.await();
				
			}
			
			return msgList.poll();
		}
		finally {
			lock.unlock();
		}
	}
	
	public void pushMsg(String msg)
	{
		lock.lock();
		try
		{
			msgList.addLast(msg);
			cond.signal();
		}
		finally{
			lock.unlock();
		}
		
	}

}
