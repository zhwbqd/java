package zhwb.study.juc.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author jack.zhang
 * @since 2015/3/9
 */
public class ReadWriteLockSample {

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private int val;

    public void write(int incr) {
        try {
            readWriteLock.writeLock().lock();
            val += incr;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public int read() {
        try {
            readWriteLock.readLock().lock();
            return val;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }


    public static void main(String[] args) {
        ReadWriteLockSample sample = new ReadWriteLockSample();

        for (int i = 0; i < 10; i++) {
            new Thread(new ReadThread(sample)).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(new WriteThread(sample)).start();
        }

    }

    static class ReadThread implements Runnable {

        private ReadWriteLockSample val;

        ReadThread(ReadWriteLockSample val) {
            this.val = val;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(val.read());
                System.out.println("read success, " + Thread.currentThread().getName());
            }
        }
    }

    static class WriteThread implements Runnable {

        private ReadWriteLockSample val;

        WriteThread(ReadWriteLockSample val) {
            this.val = val;
        }

        @Override
        public void run() {
            while (true) {
                val.write(1);
                System.out.println("read success, " + Thread.currentThread().getName());
            }
        }
    }
}
