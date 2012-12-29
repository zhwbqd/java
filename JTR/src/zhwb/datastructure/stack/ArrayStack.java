package zhwb.datastructure.stack;

/**
 * 使用 数组 实现栈
 * @author jzj
 *
 * @param <E>
 */
public class ArrayStack<E> implements Stack<E> {
	private E[] data;
	private int top = -1;//指向栈顶元素
	private int size = 3;

	public ArrayStack() {
		data = (E[]) new Object[size];
	}

	//入栈
	public void push(E e) {
		if ((top + 1) == size) {
			throw new IllegalStateException("stack full...");
		}
		data[++top] = e;
	}

	//出栈
	public E pop() {
		if (top == -1) {
			throw new IllegalStateException("stack empty...");
		}
		E tmp = data[top];
		//用完后释放对象，加快垃圾回收，防止大的对象不用发生内存泄露
		data[top] = null;
		top--;
		return tmp;
	}

	//取栈顶元素
	public E peek() {
		if (top == -1) {
			throw new IllegalStateException("stack empty...");
		}
		return data[top];
	}

	//当前栈大小
	public int size() {
		return top + 1;
	}

	//栈是否为空
	public boolean isEmpty() {
		return top == -1;
	}
}
