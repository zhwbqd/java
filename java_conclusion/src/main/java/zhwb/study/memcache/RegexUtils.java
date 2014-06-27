package zhwb.study.memcache;

import java.util.regex.Pattern;

/**
 * utils for regex
 *
 * @author dan.shan
 * @since 2014-02-11 5:36 PM
 */
public class RegexUtils {

    /**
     * 身份证正则表达式
     */
    private static final Pattern ID_NUMBER_PATTERN = Pattern.compile("^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$");
    /**
     * 手机正则表达式
     */
    private static final Pattern MOBILE_PTN = Pattern.compile("^1[3458]\\d{9}$");
    /**
     * email 正则表达式
     */
    private static final Pattern EMAIL_PTN = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");

    public static boolean isMobile(String str) {
        return str != null && MOBILE_PTN.matcher(str).find();
    }

    public static boolean isEmail(String str) {
        return str != null && EMAIL_PTN.matcher(str).find();
    }

    public static boolean isIdNumber(String str) {
        return str != null && ID_NUMBER_PATTERN.matcher(str).find();
    }

}
