
package zhwb.study.mailsender.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zhwb.study.mailsender.bean.ResponseStatus;

/**
 * The Class ThreadPoolManager, singleton, Use retry police to handle saturation.
 */
public final class ThreadPoolManager
{

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(ThreadPoolManager.class);

    /** The Constant DEFAULT_CORE_POOL_SIZE. */
    private static final String DEFAULT_CORE_POOL_SIZE = String.valueOf(Runtime.getRuntime().availableProcessors() + 1);

    /** The Constant DEFAULT_MAX_POOL_SIZE. */
    private static final String DEFAULT_MAX_POOL_SIZE = String.valueOf(Runtime.getRuntime().availableProcessors() * 2);

    /** The Constant DEFAULT_KEEP_ALIVE_TIME. */
    private static final String DEFAULT_KEEP_ALIVE_TIME = "0";

    /** The Constant DEFAULT_WORK_QUEUE_SIZE. */
    private static final String DEFAULT_WORK_QUEUE_SIZE = "500";

    private static final String DEFAULT_DELAY_TIME = "30";

    /** The Constant PROPERTIES_NAME. */
    private static final String PROPERTIES_NAME = "/poolConfig.properties";

    /** The Constant CORE_POOL_SIZE_PROP. */
    private static final String CORE_POOL_SIZE_PROP = "core_pool_size";

    /** The Constant MAX_POOL_SIZE_PROP. */
    private static final String MAX_POOL_SIZE_PROP = "max_pool_size";

    /** The Constant KEEP_ALIVE_TIME_PROP. */
    private static final String KEEP_ALIVE_TIME_PROP = "keep_alive_time";

    /** The Constant WORK_QUEUE_SIZE_PROP. */
    private static final String WORK_QUEUE_SIZE_PROP = "work_queue_size";

    private static final String DELAY_TIME_PROP = "delay_time";

    /** The Constant CORE_POOL_SIZE. */
    private static final int CORE_POOL_SIZE;

    /** The Constant MAX_POOL_SIZE. */
    private static final int MAX_POOL_SIZE;

    /** The Constant KEEP_ALIVE_TIME. */
    private static final int KEEP_ALIVE_TIME;

    /** The Constant WORK_QUEUE_SIZE. */
    private static final int WORK_QUEUE_SIZE;

    private static final long DELAY_TIME;

    /** The thread pool. */
    private final ThreadPoolExecutor onlineThreadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(WORK_QUEUE_SIZE));

    private final ScheduledExecutorService batchThreadPool = Executors.newScheduledThreadPool(CORE_POOL_SIZE);

    /** The singleton of ThreadPoolManager. */
    private static ThreadPoolManager tpm;

    static
    {
        NotificationProperties properties = new NotificationProperties(PROPERTIES_NAME);
        DELAY_TIME = Long.parseLong(properties.getProperty(DELAY_TIME_PROP, DEFAULT_DELAY_TIME));
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
        if (tpm == null)
        {
            tpm = new ThreadPoolManager();
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
    public boolean submitTask(final Callable<ResponseStatus> newTask, final boolean isExecuteNow)
    {
        try
        {
            if (isExecuteNow)
            {
                Future<ResponseStatus> result = onlineThreadPool.submit(newTask);
            }
            else
            {
                Future<ResponseStatus> result = batchThreadPool.schedule(newTask, DELAY_TIME, TimeUnit.SECONDS);
            }
        }
        catch (RejectedExecutionException rex)
        {
            LOG.info("Thread pool is full right now, this task will retry later.");
            return false;
        }
        return true;
    }
}
