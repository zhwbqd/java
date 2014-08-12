package zhwb.test.redis.sharding;

import net.spy.memcached.DefaultHashAlgorithm;
import redis.clients.util.Hashing;
import redis.clients.util.SafeEncoder;

/**
 * Date: 2014/7/31
 * Time: 17:33
 *
 * @author jack.zhang
 */
public class KetamaHashing implements Hashing {


    @Override
    public long hash(String key) {
        return hash(SafeEncoder.encode(key));
    }

    @Override
    public long hash(byte[] key) {
        return DefaultHashAlgorithm.KETAMA_HASH.hash(new String(key));
    }
}
