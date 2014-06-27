package zhwb.study.memcache;

import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;

import java.util.Collection;
import java.util.List;

public interface MemcachedCacheService {
    public static final int DEFAULT_NONE_EXPIRES_IN = 2*365*24*60;
    
    public void set(String key, Object value);

    public void set(String key, Object value, int expiresIn);

    CASResponse setCAS(String key, long casNumber, Object value);

    public void delete(String key);

    public <T> T get(String key, Class<T> clazz);

    <T> CASValue getCASValue(String key, Class<T> clazz);

    public int getKeyExpiresIn(String key);

    public boolean exist(String key);

    public <T> List<T> gets(Collection<String> keys, Class<T> clazz);

}
