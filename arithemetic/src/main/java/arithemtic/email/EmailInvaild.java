package arithemtic.email;

import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jack.zhang
 * @since 2016/6/13 0013
 */
public class EmailInvaild {
    public static void main(String[] args) {
        Map<String, Integer[][]> spec = new HashMap<>();
        spec.put("126", new Integer[][]{{1, 1, 2, 2, 1}, {1, 3, 1, 3}});

        String email = "aa11a@126.com";

        int atIndex = email.indexOf("@");
        String mail = email.substring(0, atIndex);
        System.out.println(mail);

        String mail_company = email.substring(atIndex + 1, email.indexOf(".", atIndex));
        System.out.println(mail_company);

        Integer[] spec_mail = getMailSpec(mail);

        Integer[][] integers = spec.get(mail_company);
        if (integers != null) {
            for (Integer[] integer : integers) {
                if (ArrayUtils.toString(integer).equals(ArrayUtils.toString(spec_mail))) {
                    System.out.println("fuck");
                    return;
                }
            }
        }
    }

    private static Integer[] getMailSpec(String mail) {
        Integer[] integers = new Integer[mail.length()];
        char[] chars = mail.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'a' && chars[i] <= 'z') {
                integers[i] = 1;
            } else if (chars[i] >= 'A' && chars[i] <= 'Z') {
                integers[i] = 3;
            } else if (chars[i] >= '0' && chars[i] <= '9') {
                integers[i] = 2;
            } else {
                integers[i] = 4;
            }
        }
        return integers;
    }
}
