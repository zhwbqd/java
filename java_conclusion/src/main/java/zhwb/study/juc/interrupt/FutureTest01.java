/*
 */
package zhwb.study.juc.interrupt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * if any thread find 77777 will stop all the threads and print out the find
 * thread name
 */
public class FutureTest01 {

	public static void main(String[] args) {
		ExecutorService eService = Executors.newFixedThreadPool(5);
		List<Future<?>> futures = new ArrayList<Future<?>>();
		Status flag = new Status();
		flag.setSuccess(false);
		for (int i = 0; i < 5; i++) {
			futures.add(eService.submit(new RunFuture01(flag)));
		}
	}


}
