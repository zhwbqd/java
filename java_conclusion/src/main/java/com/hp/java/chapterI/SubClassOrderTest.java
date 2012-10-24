/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.hp.java.chapterI;

class Parent
{
    // 静态变量
    public static String p_StaticField = "父类--静态变量";

    // 变量
    public String p_Field = "父类--变量";
    // 静态初始化块
    static
    {
        System.out.println(p_StaticField);
        System.out.println("父类--静态初始化块");
    }
    // 初始化块
    {
        System.out.println(p_Field);
        System.out.println("父类--初始化块");
    }

    // 构造器
    public Parent()
    {
        System.out.println("父类--构造器");
    }
}

public class SubClassOrderTest extends Parent
{
    // 静态变量
    public static String s_StaticField = "子类--静态变量";

    // 变量
    public String s_Field = "子类--变量";
    // 静态初始化块
    static
    {
        System.out.println(s_StaticField);
        System.out.println("子类--静态初始化块");
    }
    // 初始化块
    {
        System.out.println(s_Field);
        System.out.println("子类--初始化块");
    }

    // 构造器
    public SubClassOrderTest()
    {
        System.out.println("子类--构造器");
    }

    // 程序入口
    public static void main(String[] args)
    {
        new SubClassOrderTest();
    }
}
