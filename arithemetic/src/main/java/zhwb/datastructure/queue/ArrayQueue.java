package zhwb.datastructure.queue;

/**
 * 循环队列，使用数组实现
 * @author jzj
 */
public class ArrayQueue<E> implements Queue<E> {

	private Object lock = new Object();

	// 队列大小
	private int size = 1000;

	// 队列
	private E[] arrStr = (E[]) new Object[size];

	// 队列指针
	private int head = 0;

	// 队列尾指针
	private int tail = 0;

	/**
	 * 入队
	 * @param o
	 */
	public void enqueue(E o) {
		synchronized (lock) {

			// 如果队列已满
			while ((tail + 1) % size == head) {
				try {
					System.out.println("队列已满，" + Thread.currentThread().getName()
							+ " 线程阻塞...");
					// 队列满时线程阻塞
					lock.wait();//注，这里一定要放在while条件里，因为获取锁后，条件不一定还成立
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 如果队列未满
			arrStr[tail] = o;
			// 指针下移
			tail = (tail + 1) % size;
			// 入队后通知消费线程
			lock.notifyAll();
		}
	}

	/**
	 * 出队
	 * @return
	 */
	public E dequeue() {
		synchronized (lock) {
			// 如果队列为空
			while (head == tail) {
				try {
					System.out.println("队列为空，" + Thread.currentThread().getName()
							+ " 线程阻塞...");
					// 队列空时线程阻塞
					lock.wait();//注，这里一定要放在while条件里，因为获取锁后，条件不一定还成立
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 队列非空
			E tempStr = arrStr[head];

			arrStr[head] = null;//注，这里出队后释放对象，加快回收，不然大的对象可能造内存泄露
			head = (head + 1) % size;

			// 出队后通知生产者
			lock.notifyAll();
			return tempStr;

		}
	}

	//取队列第一个
	public E front() {
		synchronized (lock) {
			// 如果队列为空
			while (head == tail) {
				try {
					System.out.println("队列为空，" + Thread.currentThread().getName()
							+ " 线程阻塞...");
					// 队列空时线程阻塞
					lock.wait();//注，这里一定要放在while条件里，因为获取锁后，条件不一定还成立
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 队列非空
			return arrStr[head];
		}
	}

	//队列是否为空
	public boolean isEmpty() {
		return head == tail;
	}

	//队列大小
	public int size() {

		return Math.abs(tail - head);
	}
}
