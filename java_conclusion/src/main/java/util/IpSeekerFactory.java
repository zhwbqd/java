package util;


/**
 * Date: 2014/8/12
 * Time: 16:34
 *
 * @author jack.zhang
 */
public class IpSeekerFactory {

    private static IPSeeker ipSeeker = IPSeeker.getInstance();

    public static String getCountry(String ip) {
        return ipSeeker.getCountry(ip);
    }

}
