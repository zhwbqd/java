package zhwb.study.juc.atmo;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import org.junit.Test;

public class AtomicIntegerFieldUpdaterDemo {

	class DemoData {
		public volatile int value1 = 1;
		volatile int value2 = 2;
		protected volatile int value3 = 3;
		private volatile int value4 = 4;
	}

	AtomicIntegerFieldUpdater<DemoData> getUpdater(String fieldName) {
		return AtomicIntegerFieldUpdater.newUpdater(DemoData.class, fieldName);
	}

	@Test
	public void doit() {
		DemoData data = new DemoData();
		assertEquals(getUpdater("value1").getAndSet(data, 10), 1);
		assertEquals(getUpdater("value2").incrementAndGet(data), 3);
		// assertEquals(getUpdater("value3").decrementAndGet(data), 2);
		// assertTrue(getUpdater("value4").compareAndSet(data, 4, 5));
	}

	public static void main(String[] args) {
		AtomicIntegerFieldUpdaterDemo demo = new AtomicIntegerFieldUpdaterDemo();
		demo.doit();
	}
}
