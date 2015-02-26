package zhwb.study.unsafe;

import sun.misc.Unsafe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jack.zhang
 * @since 2015/2/26
 */
public class ShallowCopy {

    private static final Unsafe unsafe = UnSafeFactory.getInstance();

    public static void main(String[] args) {
        List<String> abc = new ArrayList<>();
        abc.add("a");
        abc.add("b");
        abc.add("c");

        List<String> anotherABC = shallowCopy(abc);
        anotherABC.add("d");

        System.out.println(abc.size());
    }

    static <T> T  shallowCopy(T obj) {
        long size = SizeOfUtil.sizeOf(obj);
        long start = toAddress(obj);
        long address = unsafe.allocateMemory(size);
        unsafe.copyMemory(start, address, size);
        return (T) fromAddress(address);
    }

    static long toAddress(Object obj) {
        Object[] array = new Object[] {obj};
        long baseOffset = unsafe.arrayBaseOffset(Object[].class);
        return SizeOfUtil.normalize(unsafe.getInt(array, baseOffset));
    }

    static Object fromAddress(long address) {
        Object[] array = new Object[] {null};
        long baseOffset = unsafe.arrayBaseOffset(Object[].class);
        unsafe.putLong(array, baseOffset, address);
        return array[0];
    }
}


