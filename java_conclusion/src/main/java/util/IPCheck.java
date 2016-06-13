package util;

import org.apache.http.conn.util.InetAddressUtils;

/**
 * @author jack.zhang
 * @since 2016/3/20 0020
 */
public class IPCheck {

    public static boolean validate(String ip) {
        return InetAddressUtils.isIPv4Address(ip);
    }

}
