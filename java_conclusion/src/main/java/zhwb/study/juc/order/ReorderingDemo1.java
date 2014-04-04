package zhwb.study.juc.order;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReorderingDemo1
{
    private static Lock lock = new ReentrantLock();//通过JDK5中的锁来保证线程的访问的互斥

    private static int state = 0;

    static class ThreadA extends Thread
    {
        @Override
        public void run()
        {
            for (int i = 0; i < 10;)
            {
                lock.lock();
                if (state % 3 == 0)
                {
                    System.out.print("A");
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }

    static class ThreadB extends Thread
    {
        @Override
        public void run()
        {
            for (int i = 0; i < 10;)
            {
                lock.lock();
                if (state % 3 == 1)
                {
                    System.out.print("B");
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }

    static class ThreadC extends Thread
    {
        @Override
        public void run()
        {
            for (int i = 0; i < 10;)
            {
                lock.lock();
                if (state % 3 == 2)
                {
                    System.out.print("C");
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }

    public static void main(final String[] args)
    {
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }

}
