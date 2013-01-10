package zhwb.datastructure.queue;

import java.util.NoSuchElementException;

/**
 * 使用 循环双向链 实现队列
 * 
 * @author jzj
 *
 * @param <E>
 */
public class LinkedQueue<E> implements Queue<E> {
	private class Entry {
		E element;//数据域
		Entry next;//后驱节点
		Entry previous;//前驱节点

		/**
		 * @param element 数据域
		 * @param next 后驱节点
		 * @param previous 前驱节点
		 */
		Entry(E element, Entry next, Entry previous) {
			this.element = element;
			this.next = next;
			this.previous = previous;
		}
	}

	/*
	 * 头节点，永远代表头。链中无结节时节点中的前后驱指针域指向自己，当有元素时
	 * 头节点的前驱指针域previous指向链中最后一个节点，而next指针域指向链中的
	 * 第一个节点
	 */
	private Entry header = new Entry(null, null, null);

	private int size = 0;//链表中节点个数，但不包括头节点header

	public LinkedQueue() {
		//初始化时头的前后驱指针都指向自己
		header.next = header.previous = header;
	}

	//入队，在链表中的最后位置加入元素，相当于 LinkedList 的add、addBefore、addLast方法实现
	public void enqueue(E e) {
		//在双向链后面加入元素
		Entry newEntry = new Entry(e, header, header.previous);
		//让新元素的前驱节点（新增前链的最后点）的前驱指向新加元素
		newEntry.previous.next = newEntry;
		//让新元素的后驱节点（header头节点）的前驱指向新加元素
		newEntry.next.previous = newEntry;
		size++;
	}

	//出队，从链表中的第一个元素开始出队，相当于 LinkedList 的removeFirst方法实现
	public E dequeue() {
		//要出队（删除）的节点的数据域
		E first = header.next.element;
		Entry e = header.next;
		//要删除的节点为头节点时不能删除
		if (e == header) {
			throw new NoSuchElementException();
		}

		//将删除节点的前驱节点的next域指针指向删除节点的后驱节点
		e.previous.next = e.next;
		//将删除节点的后驱节点的previous域指针指向删除节点的前驱节点
		e.next.previous = e.previous;
		size--;
		return first;
	}

	//取队列的第一个元素，相当于 LinkedList 的getFirst方法实现
	public E front() {
		if (size == 0)
			throw new NoSuchElementException();

		return header.next.element;
	}

	//队列是否为空
	public boolean isEmpty() {
		return size == 0;
	}

	//队列大小
	public int size() {
		return size;
	}
}
