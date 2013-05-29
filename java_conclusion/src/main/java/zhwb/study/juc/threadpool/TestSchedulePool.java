/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.juc.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestSchedulePool
{
    /*证明 单线程scheduledPool会阻塞 */
    private final static ScheduledExecutorService newLoopExecutor = Executors.newSingleThreadScheduledExecutor();

    public static void main(final String[] args)
    {
        Runnable command = new Runnable()
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
        };

        newLoopExecutor.scheduleAtFixedRate(command, 0, 1, TimeUnit.SECONDS);

    }

}
