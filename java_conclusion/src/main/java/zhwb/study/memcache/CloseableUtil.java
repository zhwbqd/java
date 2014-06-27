package zhwb.study.memcache;

import java.io.Closeable;
import java.io.IOException;

/**
 * Date: 14-3-5
 * Time: 下午5:38
 *
 * @author jack.zhang
 */
public abstract class CloseableUtil {

    public static void handleCloseable(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            //do nothing
        }
    }
}
