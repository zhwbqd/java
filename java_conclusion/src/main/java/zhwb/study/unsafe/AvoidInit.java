package zhwb.study.unsafe;

/**
 * 通过allocateInstance进行内存分配, 不进行对象的初始化及构造器
 *
 * @author jack.zhang
 * @since 2015/2/25
 */
public class AvoidInit {

    private static final sun.misc.Unsafe UNSAFE = UnSafeFactory.getInstance();

    private int a = -1;

    public AvoidInit() {
        this.a = 1;
    }

    public int getA() {
        return a;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        AvoidInit o1 = new AvoidInit(); // constructor
        System.out.println(o1.getA()); // prints 1

        AvoidInit o2 = AvoidInit.class.newInstance(); // reflection
        System.out.println(o2.getA()); // prints 1

        AvoidInit o3 = (AvoidInit) UNSAFE.allocateInstance(AvoidInit.class); // unsafe
        System.out.println(o3.getA()); // prints 0
    }

}
