package framework.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Date: 2014/7/1
 * Time: 13:42
 *
 * @author jack.zhang
 */
public class ThreadSafeCacheMap {

    private static Cache<String, Integer> cache = CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.SECONDS).build();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        cache.get("FUCK", new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt();
            }
        });

        System.out.println(cache.getIfPresent("FUCK").equals(cache.getIfPresent("FUCK")));
        sleep(10000);
        System.out.println(cache.getIfPresent("FUCK") == null);
    }
}
