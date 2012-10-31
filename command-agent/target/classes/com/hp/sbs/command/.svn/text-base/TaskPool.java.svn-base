package com.hp.sbs.command;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskPool {
	private static TaskPool taskPool = new TaskPool();

	private ExecutorService executorService = Executors.newFixedThreadPool(5);

	private Map<String, AsynTask> taskMap = Collections.synchronizedMap(new HashMap());

	private Integer nextTaskID = 1;

	public static TaskPool instance() {
		return taskPool;
	}

	private TaskPool() {
	}

	public AsynTask submitTask(IExecutor executor) {
		AsynTask task = new AsynTask(executor);
		synchronized (nextTaskID) {
			String taskID = nextTaskID.toString();
			task.setTaskID(taskID);
			taskMap.put(taskID, task);
			nextTaskID++;
		}

		Future<String> taskFutureFuture = executorService.submit(task);
		task.setTaskResultFuture(taskFutureFuture);

		return task;

	}

	public AsynTask getTask(String taskID) {
		return taskMap.get(taskID);
	}

	public String getTaskStatus(String taskID) {
		AsynTask task = taskMap.get(taskID);
		if (task != null) {
			return task.getTaskStatus();
		} else {
			return "Not Exist";
		}
	}

}
