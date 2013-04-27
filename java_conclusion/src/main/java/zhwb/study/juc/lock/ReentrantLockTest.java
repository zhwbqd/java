/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.juc.lock;

import java.util.ArrayList;
import java.util.List;

public class ReentrantLockTest
{

    public static void main(final String[] args)
        throws InterruptedException
    {
        List<String> l = new ArrayList<String>();
        List<String> threadsafe = new ThreadSafeList<String>(l);

        Runnable write = new WriteThread(threadsafe, "thread1");
        Runnable write1 = new WriteThread(threadsafe, "thread2");
        Runnable read = new ReadThread(threadsafe, "thread3");
        new Thread(write).start();
        new Thread(read).start();
        new Thread(write1).start();
    }
}

class WriteThread implements Runnable
{
    private List<String> list;

    private String name;
    @Override
    public void run()
    {
        for (int i = 0; i < 3; i++)
        {
            System.out.println("I am " + name + " add: " + list.add("I am writing: " + i));
            try
            {
                Thread.sleep(30);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param list
     * @param name 
     */
    public WriteThread(final List<String> list, final String name)
    {
        super();
        this.list = list;
        this.name = name;
    }
}

class ReadThread implements Runnable
{
    private List<String> list;

    private String name;
    @Override
    public void run()
    {
        for (String str : list)
        {
            System.out.println("I am " + name + " value: " + str);
            try
            {
                Thread.sleep(30);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param list
     * @param name 
     */
    public ReadThread(final List<String> list, final String name)
    {
        super();
        this.list = list;
        this.name = name;
    }
}