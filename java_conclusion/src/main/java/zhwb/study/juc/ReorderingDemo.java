package zhwb.study.juc;

import java.util.concurrent.Semaphore;

public class ReorderingDemo {

    private static final String A = "A";

    private static final String B = "B";

    private static final String C = "C";

    private final Semaphore a = new Semaphore(1);

    private final Semaphore b = new Semaphore(1);

    private final Semaphore c = new Semaphore(1);

	public static void main(final String[] args) throws Exception {

        Thread one = new Thread()
        {
            @Override
            public void run()
            {
                for (int i = 0; i < 10; i++)
                {

                System.out.print(A);
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
                System.out.print(B);}
            }
        };
        Thread three = new Thread()
        {
            @Override
            public void run()
            {
                for (int i = 0; i < 10; i++)
                {
                System.out.print(C);}
            }
        };

			one.start();
            two.start();
            three.start();
		}
	}

