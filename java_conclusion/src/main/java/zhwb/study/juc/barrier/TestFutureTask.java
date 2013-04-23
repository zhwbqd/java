package zhwb.study.juc.barrier;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFutureTask {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final ExecutorService exec = Executors.newFixedThreadPool(5);
		Callable call = new Callable() {
			public String call() throws Exception {
				// Thread.sleep(1000 * 5);
				return "Other less important but longtime things.";
			}
		};
		Future<String> task = exec.submit(call);
		// �?�?的事情
		// Thread.sleep(1000 * 3);
		System.out.println("Let’s do important things.");
		// 其他�?�?�?的事情
		String obj = task.get();
		System.out.println(obj);
		// 关闭线程池
		exec.shutdown();
	}
}
