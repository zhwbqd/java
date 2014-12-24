package framework.redis;

/**
 * 将key的生成封装在其中
 * <p/>
 * Date: 14/12/23
 * Time: 下午10:34
 *
 * @author jack.zhang
 */
public class KeyCapsule {

    private Duration duration;

    private String key;

    public KeyCapsule(Dimension dimension, Duration duration, String metaData, String... fields) {
        this.key = KeyGenerator.generateCacheKey(RedisKeyType.STR, KeyGenerator.buildKeyTimeSeries(dimension, metaData, duration, fields));
        this.duration = duration;
    }

    public String getKey() {
        return key;
    }

    public int getExpireSeconds() {
        return duration.getKeyExpire();
    }
}
