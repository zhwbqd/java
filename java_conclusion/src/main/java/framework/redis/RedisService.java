package framework.redis;

import java.util.List;
import java.util.Map;

/**
 * Redis封装类
 *
 * @author sam.yang
 * @author jack.zhang
 */
public interface RedisService {

    /**
     * 对key进行叠加, 如果key没有过期时间, 则初始化其过期时间
     *
     * @param counter the counter
     * @param duration the duration
     * @return the long
     */
    long incrAndExp(String counter, Duration duration);

    /**
     * 对key进行叠加, 如果key没有过期时间, 则初始化其过期时间,
     * <p>此方法是使用pipeline的批量方法, 故需要特别注意, counter和duration一定要数目一致且匹配</p>
     *
     * @param keyDurationMap the key duration pairs
     * @return the map 返回每个key的值
     */
    void incrAndExpBatch(List<KeyDurationPair> keyDurationMap);

    /**
     * 删除指定类型的key
     *
     * @param redisKeyType the redis key type
     * @param key the key
     */
    void delete(RedisKeyType redisKeyType, String key);

    /**
     * Set string.
     *
     * @param key the key
     * @param value the value
     * @return the string
     */
    String set(String key, String value);

    /**
     * Set string.
     *
     * @param key the key
     * @param value the value
     * @param expiresIn the expires in
     * @return the string
     */
    String set(String key, String value, int expiresIn);

    /**
     * 设置key的过期时间
     *
     * @param redisKeyType the redis key type
     * @param key the key
     * @param expiresIn the expires in
     * @return the long
     */
    Long expires(RedisKeyType redisKeyType, String key, int expiresIn);

    /**
     * 获取key的过期时间
     *
     * @param redisKeyType the redis key type
     * @param key the key
     * @return the long
     */
    long ttl(RedisKeyType redisKeyType, String key);

    /**
     * 获得计数器.
     *
     * @param counter the counter
     * @return the counter
     * @author sam.yang
     */
    public long getCounter(String counter);

    /**
     * 计数器+1.
     *
     * @param counter the counter
     * @return the long
     * @author sam.yang
     */
    public long incr(String counter);

    /**
     * 计数器加指定值.
     *
     * @param counter the counter
     * @param incrBy the incr by
     * @return the long
     * @author sam.yang
     */
    public long incrBy(String counter, long incrBy);

    /**
     * 计数器减1.
     *
     * @param counter the counter
     * @return the long
     * @author sam.yang
     */
    public long decr(String counter);

    /**
     * 计数器减指定值.
     *
     * @param counter the counter
     * @param decrBy the decr by
     * @return the long
     * @author sam.yang
     */
    public long decrBy(String counter, long decrBy);

    /**
     * 批量获取counter
     *
     * @param keyMap the key list
     * @return the counter batch
     */
    Map<String, Object> getBatch(Map<String, Class> keyMap);

    /**
     * Get string.
     *
     * @param key the key
     * @return the string
     */
    String get(String key);

    /**
     * Execute t.
     *
     * @param <T>             the type parameter
     * @param redisCallBack the redis call back
     * @return the t
     */
    <T> T execute(RedisCallBack<T> redisCallBack);

    /**
     * The type Key duration pair.
     */
    public static class KeyDurationPair{
        private String key;
        private Duration duration;
        private long incrValue = 1;

        /**
         * Instantiates a new Key duration pair.
         *
         * @param cacheKey the cache key
         * @param duration the duration
         * @param incrValue the incr value
         */
        public KeyDurationPair(String cacheKey, Duration duration, long incrValue) {
            this.key = cacheKey;
            this.duration = duration;
            this.incrValue = incrValue;
        }

        /**
         * Instantiates a new Key duration pair.
         *
         * @param cacheKey the cache key
         * @param duration the duration
         */
        public KeyDurationPair(String cacheKey, Duration duration) {
            this.key = cacheKey;
            this.duration = duration;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            KeyDurationPair that = (KeyDurationPair) o;

            return duration == that.duration && key.equals(that.key);

        }

        @Override
        public int hashCode() {
            int result = key.hashCode();
            result = 31 * result + duration.hashCode();
            return result;
        }

        /**
         * Instantiates a new Key duration pair.
         */
        public KeyDurationPair() {
        }

        /**
         * Gets duration.
         *
         * @return the duration
         */
        public Duration getDuration() {
            return duration;
        }

        /**
         * Sets duration.
         *
         * @param duration the duration
         */
        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        /**
         * Gets incr value.
         *
         * @return the incr value
         */
        public long getIncrValue() {
            return incrValue;
        }

        /**
         * Sets incr value.
         *
         * @param incrValue the incr value
         */
        public void setIncrValue(long incrValue) {
            this.incrValue = incrValue;
        }

        /**
         * Add incr value.
         *
         * @param incrValue the incr value
         */
        public void addIncrValue(long incrValue) {
            this.incrValue += incrValue;
        }

        /**
         * Gets key.
         *
         * @return the key
         */
        public String getKey() {
            return key;
        }

        /**
         * Sets key.
         *
         * @param key the key
         */
        public void setKey(String key) {
            this.key = key;
        }
    }
}
