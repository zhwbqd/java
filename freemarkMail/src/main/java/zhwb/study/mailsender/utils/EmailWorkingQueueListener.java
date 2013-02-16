/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.mailsender.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The Class EmailWorkingQueueManager.
 */
public final class EmailWorkingQueueListener
{

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(EmailWorkingQueueListener.class);

    /** The Constant EMAIL_WORKING_QUEUE_SIZE. */
    private static final String EMAIL_WORKING_QUEUE_SIZE = "email_working_queue_size";

    /** The Constant DEFAULT_EMAIL_WORKING_QUEUE_SIZE. */
    private static final String DEFAULT_EMAIL_WORKING_QUEUE_SIZE = "500";

    /** The Constant PROPERTIES_NAME. */
    private static final String PROPERTIES_NAME = "/poolConfig.properties";

    /** The Constant QUEUE_SZIE. */
    private static final int QUEUE_SZIE = Integer.valueOf(new NotificationProperties(PROPERTIES_NAME).getProperty(
            EMAIL_WORKING_QUEUE_SIZE, DEFAULT_EMAIL_WORKING_QUEUE_SIZE));

    /** The queue mgr. */
    private static EmailWorkingQueueListener queueMgr = new EmailWorkingQueueListener();

    /** The working queue. */
    private final BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<Runnable>(QUEUE_SZIE);

    /** The retry queue. */
    private final BlockingQueue<Runnable> retryQueue = new ArrayBlockingQueue<Runnable>(QUEUE_SZIE);

    /** The executor. */
    private final ExecutorService singleLoopExecutor = Executors.newSingleThreadExecutor();

    private final ScheduledExecutorService retryExecutor = Executors.newScheduledThreadPool(1);

    /**
     * Instantiates a new email working queue manager.
     */
    private EmailWorkingQueueListener()
    {
        singleLoopExecutor.execute(new Runnable()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        Runnable task = workingQueue.take();
                        if (!ThreadPoolManager.getInstance().submitTask(task))
                        {
                            if (!retryQueue.offer(task))
                            {
                                new Thread(task).start();
                            }
                        }
                    }
                    catch (InterruptedException e)
                    {
                        LOG.error("Interrupted Exception: {}", e.getMessage());
                    }
                }
            }
        });

        retryExecutor.scheduleAtFixedRate(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Runnable task = retryQueue.take();
                    if (!ThreadPoolManager.getInstance().submitTask(task))
                    {
                        retryQueue.put(task);
                    }
                }
                catch (InterruptedException e)
                {
                    LOG.error("Interrupted Exception: {}", e.getMessage());
                }
            }
        }, 30, 30, TimeUnit.SECONDS);
    }

    /**
     * Gets the single instance of EmailWorkingQueueManager.
     *
     * @return single instance of EmailWorkingQueueManager
     */
    public static EmailWorkingQueueListener getInstance()
    {
        return queueMgr;
    }

    /**
     * Adds the into queue.
     *
     * @param sendTask the send task
     * @return true, if successful
     */
    public boolean addIntoQueue(final Runnable sendTask)
    {
        return workingQueue.offer(sendTask);
    }

    public boolean addIntoRetryQueue(final Runnable retryTask)
    {
        return retryQueue.offer(retryTask);

    }
}
