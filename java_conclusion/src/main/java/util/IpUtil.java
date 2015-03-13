package util;

import com.google.common.collect.Lists;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public abstract class IpUtil {

    //将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理  
    public static long ipToLong(String strIp) {
        if(strIp == null || strIp.trim().length() == 0){
            return 0;
        }
        strIp = strIp.trim();
        long[] ip = new long[4];  
        //先找到IP地址字符串中.的位置  
        int position1 = strIp.indexOf(".");  
        int position2 = strIp.indexOf(".", position1 + 1);  
        int position3 = strIp.indexOf(".", position2 + 1);  
        //将每个.之间的字符串转换成整型  
        ip[0] = Long.parseLong(strIp.substring(0, position1));  
        ip[1] = Long.parseLong(strIp.substring(position1+1, position2));  
        ip[2] = Long.parseLong(strIp.substring(position2+1, position3));  
        ip[3] = Long.parseLong(strIp.substring(position3+1));  
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];  
    }  
      
    //将十进制整数形式转换成127.0.0.1形式的ip地址  
    public static String longToIP(long longIp) {  
        StringBuffer sb = new StringBuffer("");  
        //直接右移24位  
        sb.append(String.valueOf((longIp >>> 24)));  
        sb.append(".");  
        //将高8位置0，然后右移16位  
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));  
        sb.append(".");  
        //将高16位置0，然后右移8位  
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));  
        sb.append(".");  
        //将高24位置0  
        sb.append(String.valueOf((longIp & 0x000000FF)));  
        return sb.toString();  
    }
    
    public static String getLocalIP() {
        String localIp = null;
        try {
            for (Enumeration<NetworkInterface> nis = NetworkInterface
                    .getNetworkInterfaces(); nis.hasMoreElements();) {
                NetworkInterface ni = nis.nextElement();
                if (ni.isLoopback() || ni.isVirtual() || !ni.isUp())
                    continue;
                for (Enumeration<InetAddress> ias = ni.getInetAddresses(); ias
                        .hasMoreElements();) {
                    InetAddress ia = ias.nextElement();
                    if (ia instanceof Inet6Address)
                        continue;
                    localIp = ia.getHostAddress();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localIp;
    }

    public static List<String> calRangeIp(String fromIp, String toIp) {
        if (!TypeUtils.isIPV4(fromIp) || !TypeUtils.isIPV4(toIp)) {
            return Collections.emptyList();
        }

        List<String> result = Lists.newArrayList();
        if (fromIp.equals(toIp)) {
            result.add(fromIp);
            return result;
        }

        long start = ipToLong(fromIp);
        long end = ipToLong(toIp);

        while (start <= end) {
            result.add(longToIP(start));
            start++;
        }
        return result;
    }

    /** *//**
     * @param args 
     */  
    public static void main(String[] args) {  
        String ipStr = "10.101.0.178";  
        long longIp = IpUtil.ipToLong(ipStr);  
        System.out.println("10.101.0.178 to long:" + longIp);
        System.out.println(longIp % 1000);
        System.out.println("long: " + longIp + "to IP String:"
                + IpUtil.longToIP(longIp));  
    }
}
