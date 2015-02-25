package zhwb.study.unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;

/**
 * Algorithm is the following: go through all non-static fields including all superclases,
 * get offset for each field, find maximum and add padding.
 * Probably, I missed something, but idea is clear.
 *
 * @author jack.zhang
 * @since 2015/2/25
 */
public class SizeOfUtil {

    private static final sun.misc.Unsafe UNSAFE = UnSafeFactory.getInstance();

    public static long sizeOf(Object o) {
        HashSet<Field> fields = new HashSet<Field>();
        Class c = o.getClass();
        while (c != Object.class) {
            for (Field f : c.getDeclaredFields()) {
                if ((f.getModifiers() & Modifier.STATIC) == 0) {
                    fields.add(f);
                }
            }
            c = c.getSuperclass();
        }

        // get offset
        long maxSize = 0;
        for (Field f : fields) {
            long offset = UNSAFE.objectFieldOffset(f);
            if (offset > maxSize) {
                maxSize = offset;
            }
        }

        return ((maxSize/8) + 1) * 8;   // padding
    }

    private static long normalize(int value) {
        if(value >= 0) return value;
        return (~0L >>> 32) & value;
    }

    public static long sizeOfSimply(Object object){
        return UNSAFE.getAddress(
                normalize(UNSAFE.getInt(object, 4L)) + 12L);
    }

}
