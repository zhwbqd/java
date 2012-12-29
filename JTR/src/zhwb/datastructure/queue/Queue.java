package zhwb.datastructure.queue;

/**
 * 队列接口
 * 
 * @author jzj
 * 
 * @param <E>
 */
public interface Queue<E> {
	// 入队
	public void enqueue(E e);

	// 出队
	public E dequeue();

	// 取队列第一个
	public E front();

	// 队列是否为空
	public boolean isEmpty();

	// 队列大小
	public int size();
}
