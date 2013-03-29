package zhwb.datastructure.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//循环缓冲队列，从队首放，从队尾出。牺牲数组的最后一个元素，用来判断队列是否满
public class BoundedBuffer {
	final Lock lock = new ReentrantLock();// 可重入锁
	final Condition notFull = lock.newCondition();// 缓冲空条件对象
	final Condition notEmpty = lock.newCondition();// 缓冲非空条件对象

	final Object[] items = new Object[100];// 缓冲区
	int putptr/* 队首 */, takeptr/* 队尾 */, count/* 可用个数 */;

	// 将元素放入缓冲，供生产线程调用
	public void put(Object x) throws InterruptedException {
		lock.lock();// 获取锁
		try {
			// 注，要将await置于循环中，这也wait道理一样
			while (count == items.length)
				notFull.await();// 缓冲满时等待，且释放锁
			items[putptr] = x;
			// 如果队首指针指向数组最后索引时，重新指向数组第一个元素位置
			if (++putptr == items.length)
				putptr = 0;
			++count;// 循环队列中存入的元素个数
			notEmpty.signal();// 放入元素后通知其它消费线程可以消费了
		} finally {
			lock.unlock();// 释放锁
		}
	}

	// 从缓冲取出元素，供消费线程调用
	public Object take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0)
				notEmpty.await();// 如果缓冲空则等待
			Object x = items[takeptr];
			// 如果队尾指针指向数组最后索引时，重新指向数组第一个元素位置
			if (++takeptr == items.length)
				takeptr = 0;
			--count;
			notFull.signal();// 取出后会有空间，通知生产线程
			return x;
		} finally {
			lock.unlock();
		}
	}
}
