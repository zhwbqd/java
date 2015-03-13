package util;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 字符串工具类, 区别于StringUtils
 * @author jack.zhang
 * @since 2014/10/13
 */
public abstract class TextUtils {

    public static String replaceTemplateTag(String text, String varname, String value) {
        if (text == null) {
            return null;
        }
        return text.replace("${" + varname + "}", value);
    }

    public static String replaceTemplateTag(String text, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            text = replaceTemplateTag(text, entry.getKey(), entry.getValue());
        }
        return text;
    }

    public static String subByFirstUnderline(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        int index = str.indexOf("_");
        if (index < 0) {
            return str;
        }
        return str.substring(index + 1);
    }

    public static String calcRateAndFormat(long up, long down) {
        if (down <= 0 || down <= 0) {
            return "0";
        }
        return String.format("%.2f", up * 1.0 / down * 100);
    }

    public static List<String> splitAndTrim(String original, String regx) {
        if (StringUtils.isBlank(original)) {
            return Collections.emptyList();
        }

        List<String> result = Lists.newArrayList();
        String[] split = original.split(regx);
        for (String s : split) {
            if (StringUtils.isNotBlank(s)) {
                result.add(s.trim());
            }
        }
        return result;
    }
}
