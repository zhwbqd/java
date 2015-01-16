package framework.memcache;

import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;
import framework.memcache.serialize.JsonProtocol;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;


public class SpyMemCASTest {


    public static void main(String[] args) throws IOException {
        final MemcachedCacheService memcachedClient = new SpyMemcachedServiceImpl(new MemcachedClient(new InetSocketAddress("192.168.201.66", 11211)), new JsonProtocol());
        final String key = "brandStoreLock";
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Thread(new Runnable() {
                @Override
                public void run() {
                    /*大家准备, 先SET好, 如果过期才执行*/
                    if (memcachedClient.get(key, Integer.class) == null) {
                        memcachedClient.set(key, 2 * 60, 0);
                        /*去拿带版本号的值*/
                        CASValue value = memcachedClient.getCASValue(key, Integer.class);
                        CASResponse casResponse = memcachedClient.setCAS(key, value.getCas(), 1);
                        if (casResponse == CASResponse.OK) {
                            System.out.println(casResponse.name() + ", start job");
                            try {
                                sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println(casResponse.name() + ", not start");
                        }
                    } else {
                        System.out.println("cannot run again!");
                    }
                }
            }));
        }
        System.out.println(memcachedClient.get(key, Integer.class));
    }
}


