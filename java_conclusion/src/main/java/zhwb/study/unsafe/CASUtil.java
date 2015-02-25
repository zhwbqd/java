package zhwb.study.unsafe;

/**
 * @author jack.zhang
 * @since 2015/2/25
 */

public class CASUtil {

    private volatile String name;

    private volatile int age; //compareAndSwapObject不能使用基本类型, 只能使用封装类型, int应该使用compareAndSwapInt

    private static final sun.misc.Unsafe UNSAFE = UnSafeFactory.getInstance();

    private static final long nameOffset = objectFieldOffset(UNSAFE, "name", CASUtil.class);
    private static final long ageOffset = objectFieldOffset(UNSAFE, "age", CASUtil.class);


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        CASUtil obj = new CASUtil();
        obj.setAge(1);
        obj.setName("a");

        System.out.println(obj.casAge(1, 2));
        System.out.println(obj.casName("a", "b"));

        System.out.println(obj.getAge());
        System.out.println(obj.getName());

    }

    boolean casName(String origin, String val) {
        return UNSAFE.compareAndSwapObject(this, nameOffset, origin, val);
    }

    boolean casAge(int origin, int val) {
        return UNSAFE.compareAndSwapInt(this, ageOffset, origin, val);
    }

    /**
     * 获得特定field的内存地址offset
     *
     * @param UNSAFE
     * @param field
     * @param klazz
     * @return
     */
    static long objectFieldOffset(sun.misc.Unsafe UNSAFE,
                                  String field, Class<?> klazz) {
        try {
            return UNSAFE.objectFieldOffset(klazz.getDeclaredField(field));
        } catch (NoSuchFieldException e) {
            // Convert Exception to corresponding Error
            NoSuchFieldError error = new NoSuchFieldError(field);
            error.initCause(e);
            throw error;
        }
    }


}
