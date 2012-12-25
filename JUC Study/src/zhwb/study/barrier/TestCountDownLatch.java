package zhwb.study.barrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCountDownLatch {
	public static void main(String[] args) throws InterruptedException {
		// å¼€å§‹çš„å€’æ•°é”?
		final CountDownLatch begin = new CountDownLatch(1);
		// ç»“æ?Ÿçš„å€’æ•°é”?
		final CountDownLatch end = new CountDownLatch(10);
		// å??å??é€‰æ‰‹
		final ExecutorService exec = Executors.newFixedThreadPool(10);
		for (int index = 0; index < 10; index++) {
			final int NO = index + 1;
			Runnable run = new Runnable() {
				public void run() {
					try {
						begin.await();
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("No." + NO + " arrived");
					} catch (InterruptedException e) {
					} finally {
						end.countDown();
					}
				}
			};
			exec.submit(run);
		}
		System.out.println("Game Start");
		begin.countDown();
		end.await();
		System.out.println("Game Over");
		exec.shutdown();
	}
}
