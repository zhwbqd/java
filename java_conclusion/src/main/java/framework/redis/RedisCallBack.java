package framework.redis;

import redis.clients.jedis.Jedis;

/**
 * @author sam.yang
 * @since 8/18/14 2:13 PM.
 */
public interface RedisCallBack<T> {
    /**
     * Execute with jedis commonds.
     *
     * @param jedis the jedis
     * @return the object
     */
    public T execute(Jedis jedis);
}
