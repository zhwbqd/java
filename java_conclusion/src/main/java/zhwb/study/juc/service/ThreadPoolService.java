package zhwb.study.juc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 线程池服务类
 * 
 * @author DigitalSonic
 */
public class ThreadPoolService {
	/**
	 * 默认线程池大小
	 */
	public static final int DEFAULT_POOL_SIZE = 5;

	/**
	 * 默认一个任务的超时时间，单位为毫秒
	 */
	public static final long DEFAULT_TASK_TIMEOUT = 1000;

	private int poolSize = DEFAULT_POOL_SIZE;
	private ExecutorService executorService;

	/**
	 * 根据给定大小创建线程池
	 */
	public ThreadPoolService(int poolSize) {
		setPoolSize(poolSize);
	}

	/**
	 * 使用线程池中的线程来执行任务
	 */
	public void execute(Runnable task) {
		executorService.execute(task);
	}

	/**
	 * 在线程池中执行所有给定的任务并取回运行结果，使用默认超时时间
	 * 
	 * @see #invokeAll(List, long)
	 */
	public List<Node> invokeAll(List<ValidationTask> tasks) {
		return invokeAll(tasks, DEFAULT_TASK_TIMEOUT * tasks.size());
	}

	/**
	 * 在线程池中执行所有给定的任务并取回运行结果
	 * 
	 * @param timeout
	 *            以毫秒为单位的超时时间，小于0表示不设定超时
	 * @see java.util.concurrent.ExecutorService#invokeAll(java.util.Collection)
	 */
	public List<Node> invokeAll(List<ValidationTask> tasks, long timeout) {
		List<Node> nodes = new ArrayList<Node>(tasks.size());
		try {
			List<Future<Node>> futures = null;
			if (timeout < 0) {
				futures = executorService.invokeAll(tasks);
			} else {
				futures = executorService.invokeAll(tasks, timeout, TimeUnit.MILLISECONDS);
			}
			for (Future<Node> future : futures) {
				try {
					nodes.add(future.get());
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return nodes;
	}

	/**
	 * 关闭当前ExecutorService
	 * 
	 * @param timeout
	 *            以毫秒为单位的超时时间
	 */
	public void destoryExecutorService(long timeout) {
		if (executorService != null && !executorService.isShutdown()) {
			try {
				executorService.awaitTermination(timeout, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			executorService.shutdown();
		}
	}

	/**
	 * 关闭当前ExecutorService，随后根据poolSize创建新的ExecutorService
	 */
	public void createExecutorService() {
		destoryExecutorService(1000);
		executorService = Executors.newFixedThreadPool(poolSize);
	}

	/**
	 * 调整线程池大小
	 * 
	 * @see #createExecutorService()
	 */
	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
		createExecutorService();
	}
}
