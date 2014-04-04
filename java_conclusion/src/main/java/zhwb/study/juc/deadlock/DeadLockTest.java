/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.juc.deadlock;

public class DeadLockTest
{
    private static Object A = new Object();

    private static Object B = new Object();

    public static void main(final String[] args)
    {
        new Thread1().start();
        new Thread2().start();
    }

    static class Thread1 extends Thread
    {
        @Override
        public void run()
        {
            while (true)
            {
                synchronized (A)
                {
                    System.out.println("Thread1: I obtain object A lock");
                    try
                    {
                        A.notify();
                        A.wait();
                        synchronized (B)
                        {
                            System.out.println("Thread1: I obtain object B lock");
                            A.notifyAll();
                            B.wait();
                        }
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Thread2 extends Thread
    {
        @Override
        public void run()
        {
            while (true)
            {
                synchronized (A)
                {
                    System.out.println("Thread2: I obtain object A lock");
                    try
                    {
                        A.notify();
                        A.wait();
                        synchronized (B)
                        {
                            System.out.println("Thread2: I obtain object B lock");
                            A.notify();
                            B.notify();
                        }
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
