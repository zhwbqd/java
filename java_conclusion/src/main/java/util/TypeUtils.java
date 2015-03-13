package util;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

/**
 * 格式判断工具类
 *
 * author: sam
 * since: 14/12/22
 */
public class TypeUtils {
    /**
     * 是否是正整数
     * @param number
     * @return
     */
    public static boolean isPositiveNumber(Number number) {
        return (number != null) && (number.intValue() > 0);
    }

    /**
     * Is not negative.
     *
     * @param number the number
     * @return the verifier
     */
    public static boolean isNotNegative(Number number) {
        return (number != null) && (number.intValue() >= 0);
    }

    /** 手机正则表达式 */
    private static final Pattern MOBILE_PTN = Pattern.compile("^1[34578]\\d{9}$");

    /**
     * The constant IP_PTN.
     */
    private static final Pattern IP_PTN = Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
            + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$");

    /**
     * Is mobile format.
     *
     * @param mobileString the mobile string
     * @return the verifier
     */
    public static boolean isMobile(String mobileString) {
        return  (mobileString != null) && (MOBILE_PTN.matcher(mobileString).find());
    }

    /**
     * Is mobile format.
     *
     * @param ip the ip
     * @return the verifier
     */
    public static boolean isIPV4(String ip) {
        return (ip != null) && (IP_PTN.matcher(ip).find());
    }

    /** email 正则表达式 */
    private static final Pattern EMAIL_PTN = Pattern
            .compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");

    /**
     * Is mail format
     * @param mailString the mail string
     * @return verifier
     */
    public static boolean isMail(String mailString) {
        return (mailString != null) && (EMAIL_PTN.matcher(mailString).find());
    }
    /** 非中文正则表达式 */
    private static final Pattern NOT_CHINA_NAME = Pattern
            .compile("^[a-zA-Z0-9_]*$");

    /**
     * not china name
     * @param name
     * @return
     */
    public static boolean isNotChinaName(String name) {
        return (StringUtils.isNotBlank(name) && (NOT_CHINA_NAME).matcher(name).find());
    }
}
