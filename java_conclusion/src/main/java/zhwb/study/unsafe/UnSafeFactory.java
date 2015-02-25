package zhwb.study.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * http://mishadoff.com/blog/java-magic-part-4-sun-dot-misc-dot-unsafe/
 *
 * @author jack.zhang
 * @since 2015/2/25
 */
public class UnSafeFactory {

    public static Unsafe getInstance()  {
        Field theUnsafeInstance;
        try {
        theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);

            return (Unsafe) theUnsafeInstance.get(Unsafe.class);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }

}
