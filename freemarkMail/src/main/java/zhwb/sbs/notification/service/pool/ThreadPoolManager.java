/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.sbs.notification.service.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * The Class ThreadPoolManager, singleton, Use retry police to handle saturation.
 */
public final class ThreadPoolManager
{

    /** The Constant DEFAULT_CORE_POOL_SIZE. */
    private static final String DEFAULT_CORE_POOL_SIZE = String.valueOf(Runtime.getRuntime().availableProcessors() + 1);

    /** The Constant DEFAULT_MAX_POOL_SIZE. */
    private static final String DEFAULT_MAX_POOL_SIZE = String.valueOf(Runtime.getRuntime().availableProcessors() * 2);

    /** The Constant DEFAULT_KEEP_ALIVE_TIME. */
    private static final String DEFAULT_KEEP_ALIVE_TIME = "0";

    /** The Constant DEFAULT_WORK_QUEUE_SIZE. */
    private static final String DEFAULT_WORK_QUEUE_SIZE = "10";

    /** The Constant PROPERTIES_NAME. */
    private static final String PROPERTIES_NAME = "/poolConfig.properties";
    
    /** The Constant CORE_POOL_SIZE_PROP. */
    private static final String CORE_POOL_SIZE_PROP = "core_pool_size";

    /** The Constant MAX_POOL_SIZE_PROP. */
    private static final String MAX_POOL_SIZE_PROP = "max_pool_size";

    /** The Constant KEEP_ALIVE_TIME_PROP. */
    private static final String KEEP_ALIVE_TIME_PROP ="keep_alive_time";

    /** The Constant WORK_QUEUE_SIZE_PROP. */
    private static final String WORK_QUEUE_SIZE_PROP ="work_queue_size";
    
    /** The Constant CORE_POOL_SIZE. */
    private static final int CORE_POOL_SIZE;

    /** The Constant MAX_POOL_SIZE. */
    private static final int MAX_POOL_SIZE;

    /** The Constant KEEP_ALIVE_TIME. */
    private static final int KEEP_ALIVE_TIME;

    /** The Constant WORK_QUEUE_SIZE. */
    private static final int WORK_QUEUE_SIZE;

    /** The thread pool. */
    private final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(WORK_QUEUE_SIZE));;
    
    /** The singleton of ThreadPoolManager. */
    private static ThreadPoolManager tpm;

    static
    {
        NotificationProperties properties = new NotificationProperties(PROPERTIES_NAME);
        CORE_POOL_SIZE = Integer.parseInt(properties.getProperty(CORE_POOL_SIZE_PROP, DEFAULT_CORE_POOL_SIZE));
        MAX_POOL_SIZE = Integer.parseInt(properties.getProperty(MAX_POOL_SIZE_PROP, DEFAULT_MAX_POOL_SIZE));
        KEEP_ALIVE_TIME = Integer.parseInt(properties.getProperty(KEEP_ALIVE_TIME_PROP, DEFAULT_KEEP_ALIVE_TIME));
        WORK_QUEUE_SIZE = Integer.parseInt(properties.getProperty(WORK_QUEUE_SIZE_PROP, DEFAULT_WORK_QUEUE_SIZE));
    }

    /**
     * Gets the single instance of ThreadPoolManager.
     *
     * @return single instance of ThreadPoolManager
     */
    public static synchronized ThreadPoolManager getInstance()
    {
        if(tpm==null){
            tpm=new ThreadPoolManager();
        }
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
     * @return true, if successful
     */
    public boolean submitTask(final Runnable newTask)
    {
        threadPool.execute(newTask);
        return true;
    }

}
