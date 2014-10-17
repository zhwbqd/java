package zhwb.study.juc.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Date: 14-9-17
 * Time: 下午7:40
 *
 * @author jack.zhang
 */
public class ScheduleServiceTest {

    static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

//    If any execution of the task
//    encounters an exception, subsequent executions are suppressed.
    public static void main(String[] args) {
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("i am running!");
                throw new RuntimeException("fuck");
            }
        }, 0, 1, TimeUnit.MINUTES);
    }
}
