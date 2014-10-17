package util.encrypt;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Date: 14-3-6
 * Time: 下午11:13
 *
 * @author jack.zhang
 */
public class EntryptTest {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        FileInputStream in1 = new FileInputStream("img.jpg");
        FileInputStream in2 = new FileInputStream("img.jpg");
        FileInputStream in3 = new FileInputStream("img.jpg");
        FileInputStream in4 = new FileInputStream("img.jpg");
        calculateSingle(in1, in2, in3, in4);
//        calculateMultiThread(in1, in2, in3, in4);
    }

    private static void calculateMultiThread(final FileInputStream in1, FileInputStream in2, FileInputStream in3, FileInputStream in4) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<String>> list = new ArrayList<Future<String>>();
        long start = System.currentTimeMillis();
        set(in1, threadPool, list);
        set(in2, threadPool, list);
        set(in3, threadPool, list);
        set(in4, threadPool, list);
        for (Future<String> stringFuture : list) {
            stringFuture.get();
        }
        System.out.println("multi: " + (System.currentTimeMillis() - start));
        System.exit(0);
    }

    private static void set(final FileInputStream inputStream, ExecutorService threadPool, List<Future<String>> list) {
        Future<String> future1 = threadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getEncrypt(inputStream);
            }
        });
        list.add(future1);
    }

    private static void calculateSingle(FileInputStream in1, FileInputStream in2, FileInputStream in3, FileInputStream in4) throws IOException {
        long start = System.currentTimeMillis();
        getEncrypt(in1);
        getEncrypt(in2);
        getEncrypt(in3);
        getEncrypt(in4);
        System.out.println("single: " + (System.currentTimeMillis() - start));
    }

    private static String getEncrypt(FileInputStream inputStream) throws IOException {
        byte[] origin = new byte[inputStream.available()];
        inputStream.read(origin);
        inputStream.close();
        return AESEncrypt.parseByte2HexStr(AESEncrypt.encrypt(origin, "fuck"));
    }

}
