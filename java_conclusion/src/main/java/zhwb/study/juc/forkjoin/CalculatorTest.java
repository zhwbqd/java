//package zhwb.study.juc.forkjoin;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.concurrent.ForkJoinPool;
//import java.util.concurrent.Future;
//import java.util.concurrent.RecursiveTask;
//
//import org.junit.Test;
//
//class CalculatorTask extends RecursiveTask<Integer> {
//
//	private static final int THRESHOLD = 100;
//	private int start;
//	private int end;
//
//	public CalculatorTask(int start, int end) {
//		this.start = start;
//		this.end = end;
//	}
//
//	@Override
//	protected Integer compute() {
//		int sum = 0;
//		if ((start - end) < THRESHOLD) {
//			for (int i = start; i < end; i++) {
//				sum += i;
//			}
//		} else {
//			int middle = (start + end) / 2;
//			CalculatorTask left = new CalculatorTask(start, middle);
//			CalculatorTask right = new CalculatorTask(middle + 1, end);
//
//			// left.fork();
//			// right.fork();
//			invokeAll(left, right);
//
//			sum = left.join() + right.join();
//		}
//		return sum;
//	}
//}
//
//public class CalculatorTest {
//	@Test
//	public void run() throws Exception {
//		ForkJoinPool forkJoinPool = new ForkJoinPool();
//		Future<Integer> result = forkJoinPool.submit(new CalculatorTask(0,
//				10000));
//
//		assertEquals(new Integer(49995000), result.get());
//	}
//}
