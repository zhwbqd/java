package zhwb.study.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Date: 14-4-4
 * Time: 下午10:37
 *
 * @author jack.zhang
 */
public class CalculateListInteger {

    private static final int THREADHOLDS = Runtime.getRuntime().availableProcessors()*2;

    public static void main(String[] args) throws InterruptedException {
        int initialCapacity = 10000000;
        List<Integer> todo = new ArrayList<Integer>(initialCapacity);
        for (int i = 0; i < initialCapacity; i++) {
            todo.add(i);
        }
        System.out.println(singleThread(todo));
        System.out.println(multiThread(todo));

    }

    private static long multiThread(final List<Integer> todo) throws InterruptedException {
        long startTs = System.currentTimeMillis();
        int size = todo.size();
        if (size <= THREADHOLDS) {
            return singleThread(todo);
        }
        int split = size / THREADHOLDS;
        AtomicLong sum = new AtomicLong(0);
        for (int start = 0, end = split;
             start < size;
             start = Math.min(start + split, size),end = Math.min(split + end,size)) {
            Thread t = new Thread(new Calculator(todo, start, end, sum));
            t.start();
            t.join();
        }
        System.out.println("multiThread. cost:" + (System.currentTimeMillis() - startTs));
        return sum.get();
    }

    private static long singleThread(List<Integer> todo) {
        long start = System.currentTimeMillis();
        long sum = 0;
        for (Integer integer : todo) {
            sum += integer;
        }
        System.out.println("singleThread. cost:" + (System.currentTimeMillis() - start));
        return sum;
    }

    private static class Calculator implements Runnable {

        private final List<Integer> list;
        private final int start;
        private final int end;
        private AtomicLong sum;

        public Calculator(List<Integer> list, int start, int end, AtomicLong sum) {

            this.list = list;
            this.start = start;
            this.end = end;
            this.sum = sum;
        }

        @Override
        public void run() {

            for (Integer integer : list.subList(start, Math.min(end, list.size()))) {
                sum.addAndGet(integer);
            }

        }
    }
}
