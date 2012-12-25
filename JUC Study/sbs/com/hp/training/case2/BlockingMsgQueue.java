package com.hp.training.case2;

import java.util.LinkedList;


public class BlockingMsgQueue {
	private Object lock = new Object();
	private static BlockingMsgQueue msgQueue = new BlockingMsgQueue();
	
	private LinkedList<String> msgList = new LinkedList();
	
	
	private BlockingMsgQueue(){};
	
	public static synchronized BlockingMsgQueue instance()
	{
		return msgQueue;
	}
	
	public String getNextMsg() throws InterruptedException
	{
		synchronized(lock)
		{
			while(msgList.size() == 0)
			{
				lock.wait();
				
				
			}
			
			return msgList.poll();
		}
	}
	
	public void pushMsg(String msg)
	{
		synchronized(lock)
		{
			msgList.addLast(msg);
			lock.notify();
		}
		
	}

}
