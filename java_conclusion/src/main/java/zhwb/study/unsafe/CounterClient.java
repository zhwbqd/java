package zhwb.study.unsafe;

import sun.misc.Unsafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @since 2015/4/12 0012
 */
public class CounterClient implements Runnable {
    private Counter c;
    private int num;

    public CounterClient(Counter c, int num) {
        this.c = c;
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            c.increment();
        }
    }

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException {
        int NUM_OF_THREADS = 1000;
        int NUM_OF_INCREMENTS = 100000;
        ExecutorService service = Executors.newFixedThreadPool(NUM_OF_THREADS);
//        Counter counter = new Counter() {
//            private long counter = 0;
//            private ReentrantReadWriteLock.WriteLock lock = new ReentrantReadWriteLock().writeLock();
//
//            @Override
//            public void  increment() {
//                lock.lock();
//                counter++;
//                lock.unlock();
//            }
//
//            @Override
//            public long getCounter() {
//                return counter;
//            }
//        };

//        Counter counter = new Counter() {
//            AtomicLong counter = new AtomicLong(0);
//
//            @Override
//            public void  increment() {
//                counter.incrementAndGet();
//            }
//
//            @Override
//            public long getCounter() {
//                return counter.get();
//            }
//        };

        Counter counter = new Counter() {
            private volatile long counter = 0;
            private Unsafe unsafe;
            private long offset;

            {
                unsafe = UnSafeFactory.getInstance();
                offset = unsafe.objectFieldOffset(this.getClass().getDeclaredField("counter"));
            }

            @Override
            public void  increment() {
                long before = counter;
                while (!unsafe.compareAndSwapLong(this, offset, before, before + 1)) {
                    before = counter;
                }
            }

            @Override
            public long getCounter() {
                return counter;
            }
        };
        long before = System.currentTimeMillis();
        for (int i = 0; i < NUM_OF_THREADS; i++) {
            service.submit(new CounterClient(counter, NUM_OF_INCREMENTS));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);
        long after = System.currentTimeMillis();
        System.out.println("Counter result: " + counter.getCounter());
        System.out.println("Time passed in ms:" + (after - before));
    }
}
