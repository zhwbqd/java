package zhwb.study.ipaddress;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author jack.zhang
 * @since 2014/10/17
 */
public class RetrieveLocalHost {
    public static void main(String[] args) throws SocketException {

        //此类表示一个由名称和分配给此接口的 IP 地址列表组成的网络接口
        Enumeration<NetworkInterface> netInterfaces = NetworkInterface
                .getNetworkInterfaces();
        //遍历
        while (netInterfaces.hasMoreElements()) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> ips = ni.getInetAddresses();
            while (ips.hasMoreElements()) {
                InetAddress ip = ips.nextElement();
                if (ip.isLoopbackAddress()) {
                    continue;
                }
                if (!ip.getHostAddress().contains(":")) {
                    //获得Ip地址
                    System.out.println(ip.getHostAddress());
                }
            }
        }

    }
}
