/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.threadpool.self;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * The Class ThreadPoolManager, singleton, Use retry police to handle saturation
 */
public class ThreadPoolManager
{

    /** The singleton of ThreadPoolManager. */
    private static ThreadPoolManager tpm = new ThreadPoolManager();

    /** The Constant CORE_POOL_SIZE. */
    private final static int CORE_POOL_SIZE = 4;

    /** The Constant MAX_POOL_SIZE. */
    private final static int MAX_POOL_SIZE = 10;

    /** The Constant KEEP_ALIVE_TIME. */
    private final static int KEEP_ALIVE_TIME = 0;

    /** The Constant WORK_QUEUE_SIZE. */
    private final static int WORK_QUEUE_SIZE = 10;

    /** The thread pool. */
    final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(WORK_QUEUE_SIZE), new RetryExecutionHandler());

    /**
     * Gets the single instance of ThreadPoolManager.
     *
     * @return single instance of ThreadPoolManager
     */
    public static ThreadPoolManager getInstance()
    {
        return tpm;
    }

    /**
     * Instantiates a new thread pool manager.
     */
    private ThreadPoolManager()
    {
    }

    /**
     * Submit task.
     *
     * @param newTask the new task
     */
    public void submitTask(final Runnable newTask)
    {
        threadPool.execute(newTask);
    }
}
