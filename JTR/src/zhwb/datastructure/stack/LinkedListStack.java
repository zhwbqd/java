package zhwb.datastructure.stack;

import java.util.LinkedList;

/**
 * 使用 LinkedList 实现栈
 * 
 * @author jzj
 * 
 * @param <E>
 */
public class LinkedListStack<E> implements Stack<E> {
	private LinkedList<E> list = new LinkedList<E>();

	// 入栈
	public void push(E e) {
		list.addFirst(e);
	}

	// 出栈
	public E pop() {
		return list.removeFirst();
	}

	// 取栈顶元素
	public E peek() {
		return list.getFirst();
	}

	// 当前栈大小
	public int size() {
		return list.size();
	}

	// 栈是否为空
	public boolean isEmpty() {
		return list.isEmpty();
	}
}
