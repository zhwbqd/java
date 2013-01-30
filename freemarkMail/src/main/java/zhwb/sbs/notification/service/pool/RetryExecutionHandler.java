
package zhwb.sbs.notification.service.pool;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class RetryExecutionHandler.
 */
public final class RetryExecutionHandler implements RejectedExecutionHandler
{

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(RetryExecutionHandler.class);

    /** The retryscheduler. */
    private final ScheduledExecutorService retryscheduler = Executors.newScheduledThreadPool(1);

    /** The reoperate queue. */
    private final Queue<Runnable> reoperateQueue = new ConcurrentLinkedQueue<Runnable>();

    /**
     * Instantiates a new retry execution handler.
     */
    public RetryExecutionHandler()
    {
        retryscheduler.scheduleAtFixedRate(new Runnable()
        {
            @Override
            public void run()
            {
                Runnable task = reoperateQueue.poll();
                if (task != null)
                {
                    ThreadPoolManager.getInstance().submitTask(task);
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    /** {@inheritDoc}
     *  @see java.util.concurrent.RejectedExecutionHandler#rejectedExecution(java.lang.Runnable, java.util.concurrent.ThreadPoolExecutor)
     */
    @Override
    public void rejectedExecution(final Runnable r, final ThreadPoolExecutor executor)
    {
        if (!executor.isShutdown())
        {
            LOG.info("Thread pool is full right now, this task will retry later.");
            reoperateQueue.offer(r);
        }
        else
        {
            throw new RejectedExecutionException();
        }
    }


}
