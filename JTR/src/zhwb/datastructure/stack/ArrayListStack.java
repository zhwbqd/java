package zhwb.datastructure.stack;

import java.util.ArrayList;

/**
 * 使用 ArrayList 实现栈
 * @author jzj
 *
 * @param <E>
 */
public class ArrayListStack<E> implements Stack<E> {
	private ArrayList<E> list = new ArrayList<E>();

	//入栈
	public void push(E e) {
		list.add(e);//ArrayList默认就是在数组尾加入元素，从后面进
	}

	//出栈
	public E pop() {
		return list.remove(list.size() - 1);//从前面出
	}

	//取栈顶元素
	public E peek() {
		return list.get(list.size() - 1);
	}

	//当前栈大小
	public int size() {
		return list.size();
	}

	//栈是否为空
	public boolean isEmpty() {
		return list.isEmpty();
	}
}
