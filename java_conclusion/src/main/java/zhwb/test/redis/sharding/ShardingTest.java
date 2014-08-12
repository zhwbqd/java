package zhwb.test.redis.sharding;

import redis.clients.jedis.*;
import redis.clients.util.Hashing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Date: 2014/7/31
 * Time: 16:23
 *
 * @author jack.zhang
 */
public class ShardingTest {

    private static final KetamaHashing KETAMA_HASHING = new KetamaHashing();
    private static final List<String> KEYS = new ArrayList<String>() {{
        add(CachedPrefix.generateBrand("1")+"1");
        add(CachedPrefix.generateBrandSnLikeCount("2")+"1");
        add(CachedPrefix.generateGoodsLikeSet(3)+"1");
        add(CachedPrefix.generateSkuLikeCount("4")+"1");
        add(CachedPrefix.generateUserBabyUserCount()+"1");
        add(CachedPrefix.generateUserLikeBrand(6)+"1");
        add(CachedPrefix.generateUserLikeBrandSn(7)+"1");
    }};


    public static void main(String[] args) {
        testShardJedis(getConfig());
        testTwproxy(getConfig());
    }

    private static JedisPoolConfig getConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(20);
        poolConfig.setMaxActive(1000);
        poolConfig.setMaxWait(2000);
        poolConfig.setTestOnBorrow(false);
        return poolConfig;
    }

    private static void testShardJedis(JedisPoolConfig config) {
        List<JedisShardInfo> shardInfos = new ArrayList<JedisShardInfo>();
        JedisShardInfo jedisShardInfo;
        String[] hosts = new String[]{"192.168.44.19"};
        String[] ports = new String[]{"6379", "6380", "6381", "6382"};
        for (String oneHost : hosts) {
            for (String port : ports) {
                jedisShardInfo = new JedisShardInfo(oneHost, Integer.valueOf(port), 2000);
                shardInfos.add(jedisShardInfo);
            }
        }
        ShardedJedisPool jedisPool = new ShardedJedisPool(config, shardInfos, KETAMA_HASHING);
        ShardedJedis resource = jedisPool.getResource();
        int index = 0;
        for (String key : KEYS) {
            resource.set(key, String.valueOf(index));
            System.out.println(index + " shard:" + resource.get(key));
            index++;
        }

        jedisPool.returnResource(resource);
    }

    private static void testTwproxy(JedisPoolConfig config) {
        final JedisPool jedisPool = new JedisPool(config, "192.168.44.19", 9000, 2000);
        Jedis jedis = jedisPool.getResource();
        int index = 0;
        for (String key : KEYS) {
            System.out.println(index + " twproxy:" + jedis.get(key));
            index++;
        }
        jedisPool.returnResource(jedis);
    }
}
