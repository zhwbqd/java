/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.hp.java.chapterI;

public class InitialOrderTest
{
    // 静态变量
    public static String staticField = "静态变量";

    // 变量
    public String field = "变量";
    // 静态初始化块
    static
    {
        System.out.println(staticField);
        System.out.println("静态初始化块");
    }
    // 初始化块
    {
        System.out.println(field);
        System.out.println("初始化块");
    }

    // 构造器
    public InitialOrderTest()
    {
        System.out.println("构造器");
    }

    public static void main(String[] args)
    {
        new InitialOrderTest();
    }
}
