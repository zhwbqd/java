package zhwb.study.juc.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lockinterruptibly
 *
 * @author jack.zhang
 * @since 2015/5/13
 */
public class LockInterruptiblyTest {

    int a =0;

    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        final LockInterruptiblyTest test = new LockInterruptiblyTest();

        for (int i = 0; i <3; i++) {
            Thread task = new Thread() {
                @Override
                public void run() {
                    try {
                        lock.lockInterruptibly();
                        test.sendSth();
                    } catch (InterruptedException e) {
                        System.out.println("do sth after interrupted");
                    } finally {
                        lock.unlock();
                    }
                }
            };
            task.start();
            task.interrupt();
        }

    }

    public void sendSth() throws InterruptedException {
        System.out.println("send" + a);
        a++;
    }
}
