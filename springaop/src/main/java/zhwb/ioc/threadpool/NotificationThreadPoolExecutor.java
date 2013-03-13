/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.ioc.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NotificationThreadPoolExecutor extends ThreadPoolExecutor
{
    public NotificationThreadPoolExecutor(final int corePoolSize, final int maximumPoolSize, final long keepAliveTime,
            final int queueSize)
    {
        super(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, createWorkingQueue(queueSize));
    }

    private static BlockingQueue<Runnable> createWorkingQueue(final int queueSize)
    {
        if (queueSize > 0)
        {
            return new LinkedBlockingQueue<Runnable>(queueSize);
        }
        else
        {
            return new SynchronousQueue<Runnable>();
        }
    }
}
