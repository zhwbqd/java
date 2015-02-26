package zhwb.study.unsafe;

import sun.misc.Unsafe;

/**
 * 直接为对象分配内存, 使用类似于nio的directIO, 不进行任何越界检查, 使用时候谨慎, 防止jvm crash
 *
 * In fact, this technique uses off-heap memory and partially available in java.nio package.
 * Memory allocated this way not located in the heap and not under GC management,
 * so take care of it using Unsafe.freeMemory().
 * It also does not perform any boundary checks, so any illegal access may cause JVM crash.
 * It can be useful for math computations,
 * where code can operate with large arrays of data.
 * Also, it can be interesting for realtime programmers,
 * where GC delays on large arrays can break the limits.
 *
 * @author jack.zhang
 * @since 2015/2/26
 */
public class BigArrays {

    public static final Unsafe UNSAFE = UnSafeFactory.getInstance();

    public static void main(String[] args) {
        long SUPER_SIZE = (long)Integer.MAX_VALUE * 2;
        SuperArray array = new SuperArray(SUPER_SIZE);
        System.out.println("Array size:" + array.size()); // 4294967294
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            array.set((long)Integer.MAX_VALUE + i, (byte)3);
            sum += array.get((long)Integer.MAX_VALUE + i);
        }
        System.out.println("Sum of 100 elements:" + sum);  // 300
        UNSAFE.freeMemory(array.address);
    }
}


class SuperArray {
    private final static int BYTE = 1;

    private long size;
    long address;

    public SuperArray(long size) {
        this.size = size;
        address = UnSafeFactory.getInstance().allocateMemory(size * BYTE);
    }

    public void set(long i, byte value) {
        UnSafeFactory.getInstance().putByte(address + i * BYTE, value);
    }

    public int get(long idx) {
        return UnSafeFactory.getInstance().getByte(address + idx * BYTE);
    }

    public long size() {
        return size;
    }
}