package zhwb.study.juc.order;

import java.util.concurrent.Semaphore;

public class ReorderingDemo
{

    private static final String A = "A";

    private static final String B = "B";

    private static final String C = "C";

    public static void main(final String[] args)
        throws Exception
    {

        final Semaphore a = new Semaphore(1);

        final Semaphore b = new Semaphore(1);

        final Semaphore c = new Semaphore(1);

        Thread one = new Thread()
        {
            @Override
            public void run()
            {
                for (int i = 0; i < 10; i++)
                {
                    try
                    {
                        a.acquire();
                        System.out.print(A);
                        b.release();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread two = new Thread()
        {
            @Override
            public void run()
            {
                for (int i = 0; i < 10; i++)
                {
                    try
                    {
                        b.acquire();
                        System.out.print(B);
                        c.release();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread three = new Thread()
        {
            @Override
            public void run()
            {
                for (int i = 0; i < 10; i++)
                {
                    try
                    {
                        c.acquire();
                        System.out.print(C);
                        a.release();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };

        b.acquire();
        c.acquire();
        one.start();
        two.start();
        three.start();
    }
}
