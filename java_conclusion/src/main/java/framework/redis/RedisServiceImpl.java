package framework.redis;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import framework.redis.KeyGenerator;
import framework.redis.RedisCallBack;
import framework.redis.RedisKeyType;
import framework.redis.RedisService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.math.NumberUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sam.yang
 * @author jack.zhang
 * @since 8/18/14 3:00 PM.
 */
public class RedisServiceImpl implements RedisService {

    private JedisPool jedisPool;

    public RedisServiceImpl(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public String set(final String key, final String value) {
        return execute(new RedisCallBack<String>() {
            @Override
            public String execute(Jedis jedis) {
                return jedis.set(KeyGenerator.generateCacheKey(RedisKeyType.STR, key), value);
            }
        });
    }

    @Override
    public String set(final String key, final String value, final int expiresIn) {
        return execute(new RedisCallBack<String>() {
            @Override
            public String execute(Jedis jedis) {
                return jedis.setex(KeyGenerator.generateCacheKey(RedisKeyType.STR, key), expiresIn, value);
            }
        });
    }

    @Override
    public Long expires(final RedisKeyType redisKeyType, final String key, final int expiresIn) {
        return execute(new RedisCallBack<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.expire(KeyGenerator.generateCacheKey(redisKeyType, key), expiresIn);
            }
        });
    }

    @Override
    public long ttl(final RedisKeyType redisKeyType, final String key) {
        return execute(new RedisCallBack<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.ttl(KeyGenerator.generateCacheKey(redisKeyType, key));
            }
        });
    }

    @Override
    public long getCounter(final String counter) {
        return execute(new RedisCallBack<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                String value = jedis.get(KeyGenerator.generateCacheKey(RedisKeyType.STR, counter));
                return Long.valueOf(value == null ? "0" : value);
            }
        });
    }

    @Override
    public long incr(final String counter) {
        return execute(new RedisCallBack<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.incr(KeyGenerator.generateCacheKey(RedisKeyType.STR, counter));
            }
        });
    }

    @Override
    public long incrBy(final String counter, final long incrBy) {
        return execute(new RedisCallBack<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.incrBy(KeyGenerator.generateCacheKey(RedisKeyType.STR, counter), incrBy);
            }
        });
    }

    @Override
    public long incrAndExp(final String counter, final Duration duration) {
        return execute(new RedisCallBack<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                String key = KeyGenerator.generateCacheKey(RedisKeyType.STR, counter);
                long value = jedis.incr(key);
                if (jedis.ttl(key) == -1) {
                    jedis.expire(key, duration.getSeconds());
                }
                return value;
            }
        });
    }

    @Override
    public void incrAndExpBatch(final List<KeyDurationPair> keyDurationPairs) {
        if (CollectionUtils.isEmpty(keyDurationPairs)) {
            return;
        }
        execute(new RedisCallBack() {
            @Override
            public Object execute(Jedis jedis) {
                Pipeline pipelined = jedis.pipelined();
                List<KeyDurationPair> keys = new ArrayList<KeyDurationPair>(keyDurationPairs.size());
                for (KeyDurationPair keyDuration : keyDurationPairs) {
                    /*将key进行拼装, 获取ttl*/
                    String cacheKey = KeyGenerator.generateCacheKey(RedisKeyType.STR, keyDuration.getKey());
                    pipelined.ttl(cacheKey);
                    keys.add(new KeyDurationPair(cacheKey, keyDuration.getDuration(), keyDuration.getIncrValue()));
                }
                List<Object> objects = pipelined.syncAndReturnAll();
                int i = 0;
                for (KeyDurationPair keyDurationPair : keys) {
                    pipelined.incrBy(keyDurationPair.getKey(), keyDurationPair.getIncrValue());//key进行累加
                    if ((Long) objects.get(i) < 0L) {//如果key对应的ttl<0, 表示key是无过期时间或不存在,进行expire操作
                        pipelined.expire(keyDurationPair.getKey(), keyDurationPair.getDuration().getSeconds());
                    }
                    i++;
                }
                pipelined.sync();
                return null;
            }
        });
    }

    @Override
    public void incrAndExpBatchTimeSeries(final List<KeyCapsule> keyCapsules) {
        if (CollectionUtils.isEmpty(keyCapsules)) {
            return;
        }
        execute(new RedisCallBack() {
            @Override
            public Object execute(Jedis jedis) {
                Pipeline pipelined = jedis.pipelined();
                for (KeyCapsule keyCapsule : keyCapsules) {
                    /*将key进行拼装, 获取ttl*/
                    pipelined.ttl(keyCapsule.getKey());
                }
                List<Object> objects = pipelined.syncAndReturnAll();
                int i = 0;
                for (KeyCapsule keyCapsule : keyCapsules) {
                    pipelined.incr(keyCapsule.getKey());
                    if ((Long) objects.get(i) < 0L) {
                        pipelined.expire(keyCapsule.getKey(), keyCapsule.getExpireSeconds());
                    }
                    i++;
                }
                pipelined.sync();
                return null;
            }
        });
    }

    @Override
    public void delete(final RedisKeyType redisKeyType, final String key) {
        execute(new RedisCallBack() {
            @Override
            public Object execute(Jedis jedis) {
                return jedis.del(KeyGenerator.generateCacheKey(redisKeyType, key));
            }
        });
    }

    @Override
    public long decr(final String counter) {
        return execute(new RedisCallBack<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                return jedis.decr(KeyGenerator.generateCacheKey(RedisKeyType.STR, counter));
            }
        });
    }

    @Override
    public long decrBy(final String counter, final long decrBy) {
        return execute(new RedisCallBack<Long>() {

            @Override
            public Long execute(Jedis jedis) {
                return jedis.decrBy(KeyGenerator.generateCacheKey(RedisKeyType.STR, counter), decrBy);
            }
        });
    }

    @Override
    public Map<String, Object> getBatch(final Map<String, Class> keyMap) {
        return execute(
                new RedisCallBack<Map<String, Object>>() {
                    @Override
                    public Map<String, Object> execute(Jedis jedis) {
                        Map<String, Object> result = Maps.newHashMap();
                        if (MapUtils.isEmpty(keyMap)) {
                            return result;
                        }
                        List<String> keys = Lists.newArrayList();
                        Pipeline pipelined = jedis.pipelined();
                        for (Map.Entry<String, Class> entry : keyMap.entrySet()) {
                            String key = entry.getKey();
                            keys.add(key);
                            pipelined.get(KeyGenerator.generateCacheKey(RedisKeyType.STR, key));
                        }

                        List<Object> objects = pipelined.syncAndReturnAll();
                        for (int i = 0; i < objects.size(); i++) {
                            String key = keys.get(i);
                            Object value = objects.get(i);
                            result.put(key, value == null ? null : RedisUtils.convertResult(value, keyMap.get(key)));
                        }
                        return result;
                    }
                });
    }

    @Override
    public long sum(final List<String> keys) {
        return execute(new RedisCallBack<Long>() {
            @Override
            public Long execute(Jedis jedis) {
                Pipeline pipelined = jedis.pipelined();
                for (String key : keys) {
                    pipelined.get(KeyGenerator.generateCacheKey(RedisKeyType.STR, key));
                }
                List<Object> values = pipelined.syncAndReturnAll();

                long sum = 0L;
                for (int i = 0; i < values.size(); i++) {
                    String val = keys.get(i);
                    if (val != null && NumberUtils.isDigits(val)) {
                        sum += Long.valueOf(val);
                    }
                }
                return sum;
            }
        });
    }

    @Override
    public String get(final String key) {
        return execute(new RedisCallBack<String>() {
            @Override
            public String execute(Jedis jedis) {
                return jedis.get(KeyGenerator.generateCacheKey(RedisKeyType.STR, key));
            }
        });
    }

    @Override
    public <T> T execute(RedisCallBack<T> redisCallBack) {
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            return redisCallBack.execute(jedis);
        } catch (Exception e) {
            if (jedis != null) {
                this.jedisPool.returnBrokenResource(jedis);
            }
            throw new IllegalStateException("failed execute jedis commands", e);
        } finally {
            //如果exception, isConnection=false, 不进行还操作
            if (jedis != null && jedis.isConnected()) {
                this.jedisPool.returnResource(jedis);
            }
        }
    }

}
