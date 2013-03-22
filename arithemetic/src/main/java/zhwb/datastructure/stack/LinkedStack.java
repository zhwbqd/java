package zhwb.datastructure.stack;

/**
 * 使用 单链表 实现栈
 * @author jzj
 *
 * @param <E>
 */
public class LinkedStack<E> implements Stack<E> {
	private class Node {
		E data;//数据域
		Node next;//next域

		Node() {//栈底元素构造函数
			data = null;
			next = null;
		}

		/**
		 * 非栈底元素构造函数
		 * @param data 数据域
		 * @param next 新建节点next域指向
		 */
		Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}

		//是否到栈底元素
		boolean isEnd() {
			return data == null && next == null;
		}
	}

	/*
	 * 栈顶元素，刚开始时创建一个data与next域都为null的节点，它是一个标志性节点，
	 * 也相当于栈底，如判断栈是否为空就可以使用它：如果top指向了这个节点，就表明已
	 * 到栈底了，即栈为空
	 */
	private Node top = new Node();

	//入栈
	public void push(E data) {
		//让栈顶指向新建节点
		top = new Node(data, top);
	}

	//出栈
	public E pop() {
		//该节点可能是栈底元素，如果是则返回为null
		E data = top.data;
		if (!top.isEnd()) {//如果不是栈底，则指向下一个元素
			top = top.next;
		}
		return data;
	}

	//取栈顶元素
	public E peek() {
		//该节点可能是栈底元素，如果是则返回为null
		return top.data;
	}

	//栈大小
	public int size() {
		int size = 0;
		Node tmpTop = top;
		while (tmpTop.next != null) {
			size++;
			tmpTop = tmpTop.next;
		}
		return size;
	}

	//栈是否为空
	public boolean isEmpty() {
		return top.isEnd();
	}
}