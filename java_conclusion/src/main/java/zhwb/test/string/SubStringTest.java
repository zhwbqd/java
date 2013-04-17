/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.test.string;

public class SubStringTest
{

    /**
     * Description goes here.
     *
     * @param args
     */
    public static void main(final String[] args)
    {
        String s = "abcdefgh";
        String sub = s.substring(3, 8);
        System.out.println(sub.hashCode());
        sub = sub.intern();
        System.out.println(sub.hashCode());

    }

}
