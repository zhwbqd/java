/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.pattern;

import java.util.regex.Pattern;

public class PatternTest
{

    /**
     * Description goes here.
     *
     * @param args
     */
    public static void main(final String[] args)
    {
        String input = "Can not have $ at all {items}";
        String input1 = "I have a ${user} pattern";
        String regex = ".*\\$\\{\\w+\\}.*";

        System.out.println(Pattern.matches(regex, input));
        System.out.println(Pattern.matches(regex, input1));
        System.out.println(Pattern.matches(regex, " ${user} "));
    }

}
