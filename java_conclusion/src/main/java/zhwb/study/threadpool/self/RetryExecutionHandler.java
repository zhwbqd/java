/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.threadpool.self;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import zhwb.study.threadpool.AccessDBThread;


/**
 * The Class RetryExecutionHandler.
 */
public final class RetryExecutionHandler implements RejectedExecutionHandler
{

    /** The retryscheduler. */
    private final ScheduledExecutorService retryscheduler = Executors.newScheduledThreadPool(1);

    /** The reoperate queue. */
    private final Queue<Runnable> reoperateQueue = new ConcurrentLinkedQueue<Runnable>();

    /** The retry task thread. */
    private final Runnable retryTaskThread = new RetryTaskThread();

    /**
     * Instantiates a new retry execution handler.
     */
    public RetryExecutionHandler()
    {
        retryscheduler.scheduleAtFixedRate(retryTaskThread, 0, 1, TimeUnit.SECONDS);
    }

    /** {@inheritDoc}
     *  @see java.util.concurrent.RejectedExecutionHandler#rejectedExecution(java.lang.Runnable, java.util.concurrent.ThreadPoolExecutor)
     */
    @Override
    public void rejectedExecution(final Runnable r, final ThreadPoolExecutor executor)
    {
        if (!executor.isShutdown())
        {
            System.out.println("Thread pool is full right now, this task will retry later." + ((AccessDBThread)r).getMsg()
                    + " will put into pool later");
            reoperateQueue.offer(r);
        }
        else
        {
            throw new RejectedExecutionException();
        }
    }

    /**
     * The Class RetryTaskThread.
     */
    private class RetryTaskThread implements Runnable
    {

        /** {@inheritDoc}
         *  @see java.lang.Runnable#run()
         */
        @Override
        public void run()
        {
            if (!reoperateQueue.isEmpty())
            {
                Runnable task = reoperateQueue.poll();
                ThreadPoolManager.getInstance().submitTask(new Thread(task));
            }
        }

    }
}

