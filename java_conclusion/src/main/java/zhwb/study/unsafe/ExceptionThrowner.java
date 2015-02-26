package zhwb.study.unsafe;

import java.io.IOException;

/**
 * 不想在方法签名上声明checkedException, 可以使用UnSafe进行throw
 *
 * @author jack.zhang
 * @since 2015/2/26
 */
public class ExceptionThrowner {
    public static void main(String[] args) {
        UnSafeFactory.getInstance().throwException(new IOException());

    }
}
