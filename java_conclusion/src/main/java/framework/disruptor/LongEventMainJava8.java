//package framework.disruptor;
//
//import com.lmax.disruptor.RingBuffer;
//import com.lmax.disruptor.dsl.Disruptor;
//
//import java.nio.ByteBuffer;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;
//
//public class LongEventMainJava8 {
//    /**
//     * 用lambda表达式来注册EventHandler和EventProductor
//     * @param args
//     * @throws InterruptedException
//     */public static void main(String[] args) throws InterruptedException {
//        // Executor that will be used to construct new threads for consumers
//        Executor executor = Executors.newCachedThreadPool();
//        // Specify the size of the ring buffer, must be power of 2.
//        int bufferSize = 1024;// Construct the Disruptor
//        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, executor);
//        // 可以使用lambda来注册一个EventHandler
//        disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println("Event: " + event.getValue()));
//        // Start the Disruptor, starts all threads running
//        disruptor.start();
//        // Get the ring buffer from the Disruptor to be used for publishing.
//        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
//
//        LongEventProducer producer = new LongEventProducer(ringBuffer);
//
//        ByteBuffer bb = ByteBuffer.allocate(8);for (long l = 0; true; l++) {
//            bb.putLong(0, l);
//            ringBuffer.publishEvent((event, sequence, buffer) -> event.setValue(buffer.getLong(0)), bb);
//            Thread.sleep(1000);
//        }
//    }
//}