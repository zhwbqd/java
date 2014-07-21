package zhwb.study.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * Date: 2014/7/21
 * Time: 20:32
 *
 * @author jack.zhang
 */
public class LockMethodTest {
    public static void main(String[] args) {
        final DoIt doIt = new DoIt();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    doIt.prepare();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                doIt.doIt();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                doIt.doIt();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                doIt.doIt();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    doIt.prepare();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

/**
 * prepare的时候不能进行do操作, 多个prepare不能同时执行
 *
 * @author jack.zhang
 */
class DoIt {

    private ReentrantLock lock = new ReentrantLock();

    private volatile boolean isDone;

    public void prepare() throws InterruptedException {
        if (lock.tryLock()) {
            try {
                isDone = false;
                System.out.println("preparing...");
                isDone = true;
            } finally {
                lock.unlock();
            }
        }
    }

    public void doIt() {
        if (isDone) {
            System.out.println("doing...");
        } else {
            System.out.println("fuck");
        }
    }

}
