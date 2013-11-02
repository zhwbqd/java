package zhwb.ioc.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorFactory implements
		org.springframework.beans.factory.FactoryBean {

	public final void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	public final void setMaximumPoolSize(int maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}

	public final void setKeepAliveTime(long keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}

	public final void setUnit(TimeUnit unit) {
		this.unit = unit;
	}

	public final void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}

	private int corePoolSize;
	private int maximumPoolSize;
	private long keepAliveTime;
	private TimeUnit unit;
	private int queueSize;

	public Object getObject() throws Exception {
		return new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
				keepAliveTime, unit, createQueue(queueSize));
	}

	private BlockingQueue<Runnable> createQueue(int queueSize) {
		if (queueSize > 0) {
			return new LinkedBlockingQueue<Runnable>(queueSize);
		} else {
			return new SynchronousQueue<Runnable>();
		}
	}

	public Class<?> getObjectType() {
		return ThreadPoolExecutor.class;
	}

	public boolean isSingleton() {
		return true;
	}

}
