package zhwb.datastructure.stack;

/**
 * 栈接口
 * 
 * @author jzj
 * 
 * @param <E>
 */
public interface Stack<E> {
	// 入栈
	public void push(E e);

	// 出栈
	public E pop();

	// 取栈顶元素
	public E peek();

	// 当前栈大小
	public int size();

	// 栈是否为空
	public boolean isEmpty();
}
