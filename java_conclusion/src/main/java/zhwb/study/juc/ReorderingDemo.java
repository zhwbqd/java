package zhwb.study.juc;

public class ReorderingDemo {

    private static final String A = "A";

    private static final String B = "B";

    private static final String C = "C";

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
        for (int i = 0; i < 10; i++)
        {
			one.start();
            two.start();
            three.start();
            one.join();
            two.join();
            three.join();
		}
	}

}
