package zhwb.study.memcache;

import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;
import zhwb.study.memcache.serialize.Serialization;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SpyMemcachedServiceImpl implements MemcachedCacheService {

    private MemcachedClient client;

    private Serialization serializer;

    public SpyMemcachedServiceImpl(MemcachedClient client) {
        super();
        this.client = client;
    }

    public SpyMemcachedServiceImpl(MemcachedClient client, Serialization serializer) {
        this.client = client;
        this.serializer = serializer;
    }

    @Override
    public void set(String key, Object value) {
        this.set(key, value, DEFAULT_NONE_EXPIRES_IN);
    }

    @Override
    public void set(String key, Object value, int expiresIn) {
        if (serializer != null) {
            value = serializer.serialize(value);
        }
        client.set(key, expiresIn, value);
    }

    @Override
    public CASResponse setCAS(String key, long casNumber, Object value) {
        if (serializer != null) {
            value = serializer.serialize(value);
        }
        return client.cas(key, casNumber, value);
    }

    @Override
    public void delete(String key) {
        client.delete(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key, Class<T> clazz) {

        Object value = client.get(key);
        if (value != null && serializer != null) {
            value = serializer.deserialize(clazz, value);
        }
        return (T) value;
    }

    @Override
    public <T> CASValue getCASValue(String key, Class<T> clazz) {
        CASValue casValue = client.gets(key);
        if (casValue != null && serializer != null) {
            casValue = new CASValue<T>(casValue.getCas(), serializer.deserialize(clazz, casValue.getValue()));
        }
        return casValue;
    }

    @Override
    public int getKeyExpiresIn(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean exist(String key) {
        return client.get(key) != null;
    }

    @Override
    public <T> List<T> gets(Collection<String> keys, Class<T> clazz) {
        List<String> list = CollectionUtil.newArrayList(keys.size());
        for (String key : keys) {
            list.add(key);
        }
        Map<String, Object> value = client.getBulk(keys);

        if (value == null || value.size() == 0) {
            return null;
        }

        List<T> result = CollectionUtil.newArrayList(value.size());
        for (Entry<String, Object> entry : value.entrySet()) {
            result.add(entry.getValue() == null ? null : (T) serializer.deserialize(clazz, entry.getValue()));
        }
        return result;
    }

    public void setSerializer(Serialization serializer) {
        this.serializer = serializer;
    }

}
