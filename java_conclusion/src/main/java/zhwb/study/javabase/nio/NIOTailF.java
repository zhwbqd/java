package zhwb.study.javabase.nio;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Date: 14-1-9
 * Time: 上午11:07
 *
 * @author jack.zhang
 */
public class NIOTailF {
    public static void main(String[] args) throws Exception {
        /*if (args.length != 1) {
            System.exit(-1);
        }*/
//        readLines("pom.xml");
        read("pom.xml");
    }

    private static void readLines(String fileName) throws Exception {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            while (true) {
                String line = br.readLine();
                if (line == null) { // end of file, start polling
                    System.out.println("no file data available; sleeping..");
                    Thread.sleep(2 * 1000);
                } else {
                    System.out.println(line);
                }
            }
        } finally {
            if (br != null) br.close();
        }
    }

    private static void read(String fileName) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(100);
        FileInputStream inputStream = new FileInputStream(fileName);
        FileChannel fileChannel = inputStream.getChannel();
        fileChannel.read(buffer);
        /*判断buffer是否满了*/
        while (buffer.position() == buffer.capacity()) {
            buffer.flip();
            int remaining = buffer.remaining();
            for (int i = 0; i < remaining; i++) {
                System.out.print(new String(new byte[]{buffer.get()}));
            }
            fileChannel.read(buffer);
            System.out.println(fileChannel.position());
        }
    }
}
