package zhwb.study.unsafe;

import java.lang.reflect.Field;

/**
 * 隐藏密码
 *
 * Most of the APIs for retrieving user's password,
 * have signature as byte[] or char[]. Why arrays?
 * It is completely for security reason,
 * because we can nullify array elements after we don't need them.
 * If we retrieve password as String it can be saved like an object in memory
 * and nullifying that string just perform dereference operation.
 * This object still in memory by the time GC decide to perform cleanup.
 *
 * @author jack.zhang
 * @since 2015/2/26
 */
public class HidePassword {
    public static void main(String[] args) {

        String password = "l00k@myHor$e";
        String fake = password.replaceAll(".", "?");
        System.out.println(password); // l00k@myHor$e
        System.out.println(fake); // ????????????

        UnSafeFactory.getInstance().copyMemory(
                fake, 0L, null, ShallowCopy.toAddress(password), SizeOfUtil.sizeOf(password));

        System.out.println(password); // ????????????
        System.out.println(fake); // ????????????

    }

    /**
     * That way is not really safe. For real safety we need to nullify backed char array via reflection
     *
     * @param string
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void realSafe(String string) throws NoSuchFieldException, IllegalAccessException {
        Field stringValue = String.class.getDeclaredField("value");
        stringValue.setAccessible(true);
        char[] mem = (char[]) stringValue.get(string);
        for (int i=0; i < mem.length; i++) {
            mem[i] = '?';
        }
    }
}
