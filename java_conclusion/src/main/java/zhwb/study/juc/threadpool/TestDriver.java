/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.juc.threadpool;

public class TestDriver
{
    ThreadPoolManager tpm = ThreadPoolManager.newInstance();

    public void sendMsg(final String msg)
    {
        tpm.addLogMsg(msg + "记录一条日志 ");

    }

    public static void main(final String[] args)
        throws InterruptedException
    {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++)
        {
            new TestDriver().sendMsg(Integer.toString(i));
        }
        Thread.currentThread().join();
        long end = System.currentTimeMillis();
        System.out.println("process end, time :" + (end - start));
    }

}
