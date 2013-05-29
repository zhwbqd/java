/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestshutdownPool
{
    /*证明 单线程scheduledPool会阻塞 
     * 验证shutdown命令
     * */

    private final static ExecutorService newLoopExecutor = Executors.newFixedThreadPool(4);

    public static void main(final String[] args)
        throws InterruptedException
    {
        for (int i = 0; i < 10; i++)
        {
            newLoopExecutor.execute(new TestThread());
        }

        newLoopExecutor.shutdown();
        newLoopExecutor.awaitTermination(1, TimeUnit.SECONDS);

    }
}

class TestThread implements Runnable
{
    @Override
    public void run()
    {
        System.out.println("I am executing!");
        try
        {
            Thread.sleep(4000L);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
