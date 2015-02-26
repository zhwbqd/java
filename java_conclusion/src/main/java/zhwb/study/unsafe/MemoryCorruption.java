package zhwb.study.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 使用putXXX可以更改对象的内存内容, 功能类似于反射. 但是可以更改任何对象, 没有持有引用的也可以
 *
 * @author jack.zhang
 * @since 2015/2/26
 */
public class MemoryCorruption {
    public static void main(String[] args) throws NoSuchFieldException {
        Guard guard = new Guard();
        Guard guard1 = new Guard();
        guard.giveAccess();   // false, no access

        Unsafe unsafe = UnSafeFactory.getInstance();
        Field f = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
        unsafe.putInt(guard, unsafe.objectFieldOffset(f), 42); // memory corruption
        unsafe.putInt(guard, 16 + unsafe.objectFieldOffset(f), 42); // memory corruption for any object

        System.out.println(guard.giveAccess()); // true, access granted
        System.out.println(guard1.giveAccess()); // true, access granted
    }
}

class Guard {
    private int ACCESS_ALLOWED = 1;

    public boolean giveAccess() {
        return 42 == ACCESS_ALLOWED;
    }
}