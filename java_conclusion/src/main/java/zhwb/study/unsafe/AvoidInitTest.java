package zhwb.study.unsafe;

/**
 * @author jack.zhang
 * @since 2015/2/25
 */
public class AvoidInitTest {

    private static final sun.misc.Unsafe UNSAFE = UnSafeFactory.getInstance();

    private int a;

    public AvoidInitTest() {
        this.a = 1;
    }

    public int getA() {
        return a;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        AvoidInitTest o1 = new AvoidInitTest(); // constructor
        System.out.println(o1.getA()); // prints 1

        AvoidInitTest o2 = AvoidInitTest.class.newInstance(); // reflection
        System.out.println(o2.getA()); // prints 1

        AvoidInitTest o3 = (AvoidInitTest) UNSAFE.allocateInstance(AvoidInitTest.class); // unsafe
        System.out.println(o3.getA()); // prints 0
    }

}
