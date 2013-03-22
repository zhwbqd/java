package zhwb.datastructure.queue;

import java.util.LinkedList;

/**
 * 使用 LinkedList 实现队列
 * @author jzj
 *
 * @param <E>
 */
public class LinkedListQueue<E> implements Queue<E> {
	private LinkedList<E> linkedList = new LinkedList<E>();

	//入队
	public void enqueue(E e) {
		linkedList.addLast(e);
	}

	//出队
	public E dequeue() {
		return linkedList.removeFirst();
	}

	//取队列第一个
	public E front() {
		return linkedList.getFirst();
	}

	//队列是否为空
	public boolean isEmpty() {
		return linkedList.isEmpty();
	}

	//队列大小
	public int size() {
		return linkedList.size();
	}
}
