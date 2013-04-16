/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.javabase.collection;

public class StackInvocation
{
    public void print(int i)
    {
        if (i >= 2)
        {
            print(--i); //i--,i-1
        }
        System.out.println(i);
    }

    public static void main(final String[] args)
    {
        new StackInvocation().print(6);
    }
}
