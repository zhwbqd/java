/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.hp.java.chapterI;

public class A
{
    public static int a = B.b + 2;

    public static void main(String[] args)
    {
        System.out.println(A.a);
        System.out.println(B.b);

    }
}
