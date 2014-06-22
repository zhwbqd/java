package zhwb.study.juc.sephmo;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

/**
 * Date: 14-6-22
 * Time: 下午7:49
 *
 * @author jack.zhang
 */
public class SemaphoreTest {


    public static void main(String[] args) {
        final ServiceHolder serviceHolder = new ServiceHolder();
        Executor e = Executors.newFixedThreadPool(2);
        Worker worker = new Worker(serviceHolder);
        e.execute(worker);
        e.execute(worker);
        while (true) {
            if (serviceHolder.isResourceFree()) {
                e.execute(new Worker(serviceHolder));
                break;
            }
        }
    }


    static class ServiceHolder {
        private Semaphore resources = new Semaphore(1);

        public void runningResources() throws InterruptedException {
            if (resources.tryAcquire()) {
                System.out.println("i am running a resources, cost more than 15s.");
                sleep(1500 + new Random().nextInt(100));
                resources.release();
            } else {
                System.out.println("cannot running again until resource release");
            }
        }

        public boolean isResourceFree() {
            return resources.availablePermits() > 0;
        }

    }

    private static class Worker implements Runnable {
        private final ServiceHolder serviceHolder;

        public Worker(ServiceHolder serviceHolder) {
            this.serviceHolder = serviceHolder;
        }

        public void run() {
            try {
                serviceHolder.runningResources();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }
}