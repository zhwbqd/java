package com.hp.training.case1;

import java.util.LinkedList;

public class MsgQueue {
	
	private static MsgQueue msgQueue = new MsgQueue();
	
	private LinkedList<String> msgList = new LinkedList();
	
	private MsgQueue(){};
	
	public static synchronized MsgQueue instance()
	{
		return msgQueue;
	}
	
	public synchronized String getNextMsg()
	{
		
		if(msgList.size() > 0)
		{
			return msgList.poll();
		}
		else
		{
			return null;
		}
	}
	
	public synchronized void pushMsg(String msg)
	{
		msgList.addLast(msg);
	}
	

}
