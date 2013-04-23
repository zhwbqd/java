package zhwb.study.juc.barrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SelfTestCountDownLatch {
	public static void main(final String[] args) throws InterruptedException {
		// 开始的倒数�?
        final CountDownLatch beginLatch = new CountDownLatch(1);
		// 结�?�的倒数�?
        final CountDownLatch endLatch = new CountDownLatch(10);
		// �??�??选手
		final ExecutorService exec = Executors.newFixedThreadPool(10);
		for (int index = 0; index < 10; index++) {
			final int NO = index + 1;
			Runnable run = new Runnable() {
				public void run() {
					try {
                        beginLatch.await();
                        long start = System.currentTimeMillis();
						Thread.sleep((long) (Math.random() * 1000));
                        long endTime = System.currentTimeMillis();
                        System.out.println("No." + NO + " arrived" + " takes" + (endTime - start) + "ms");
					} catch (InterruptedException e) {
					} finally {
                        endLatch.countDown();
					}
				}
			};
			exec.execute(run);
		}
		System.out.println("Game Start");
        long start = System.currentTimeMillis();
        beginLatch.countDown();
        endLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("Game Over" + " spend " + (endTime - start) + "ms");
		exec.shutdown();
	}
}
