/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package jtr.date;

public class StringIntern
{
    public static void main(String[] args)
    {
        String a = "a";
        String b = "b";
        System.out.println(a.intern());
        System.out.println(b.intern());
    }
}
