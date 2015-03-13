package util;

import java.io.*;
import java.util.List;

/**
 * @author jack.zhang
 * @since 2015/3/13
 */
public class GetIp {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("ipchina.txt"));

        BufferedWriter writer = new BufferedWriter(new FileWriter("target/china-ipset.txt"));

        String line;
        while ((line = reader.readLine()) != null) {
            List<String> ips = TextUtils.splitAndTrim(line, " ");
            if (ips.isEmpty() || ips.size() != 2) {
                System.out.println("start parse: " + line);
                continue;
            }

            List<String> list = IpUtil.calRangeIp(ips.get(0), ips.get(1));

            for (String s : list) {
                writer.write(s);
            }
            System.out.println("fromIp: " + ips.get(0) + ", toIp: " + ips.get(1));
            writer.flush();
        }

        writer.close();
    }
}
