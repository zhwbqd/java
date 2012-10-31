package com.hp.sbs.command;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsynTask implements Callable{
	
	private IExecutor executor; 
	
	private String taskStatus = "NotStarted"; 
	
	private Future<String> taskResultFuture;
	
	private String taskID;
	
	private ResultHandler resultHandler= null;
	

	public ResultHandler getResultHandler() {
		return resultHandler;
	}

	public void setResultHandler(ResultHandler resultHandler) {
		this.resultHandler = resultHandler;
	}

	public synchronized String getTaskID() {
		return taskID;
	}

	public synchronized void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public AsynTask(IExecutor executor)
	{
		this.executor = executor; 
		
	}
	
	public IExecutor getExecutor() {
		return executor;
	}

	public void setExecutor(IExecutor executor) {
		this.executor = executor;
	}

	public synchronized String getTaskStatus() {
		return taskStatus;
	}

	public synchronized void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	

	public String waitForTaskResult() throws Exception{
		try {
			return (String)taskResultFuture.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} 
	}

	public Object call() throws Exception {
		this.setTaskStatus("In Progress");
		
		try
		{
			String result = executor.exec();
			this.setTaskStatus("Done");
			
			return result;
			
		}
		catch(Exception ex)
		{
			this.setTaskStatus("Failure");
			
			throw new ExecutionException(ex);
		}
	}

	public void setTaskResultFuture(Future<String> taskResultFuture) {
		this.taskResultFuture = taskResultFuture;
	} 
	
	
	
	

}
