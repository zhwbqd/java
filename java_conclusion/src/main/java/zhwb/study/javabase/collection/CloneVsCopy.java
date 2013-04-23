/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.javabase.collection;


public class CloneVsCopy
{
    public static void main(final String[] args)
    {
        byte[] b1 = new byte[] {1, 2, 3};
        byte[] c1 = new CloneVsCopy().arrayCopySystem(b1);
        System.out.println(b1 == c1);
        byte[] c2 = new CloneVsCopy().clone(b1);
        System.out.println(b1 == c2);
        b1[0] = 127;
        for (byte b : b1)
        {
            System.out.println(b);
        }
        for (byte b : c1)
        {
            System.out.println(b);
        }
        for (byte b : c2)
        {
            System.out.println(b);
        }
    }

    public byte[] arrayCopySystem(final byte[] content)
    {
        if (content == null || content.length == 0)
        {
            return null;
        }
        byte[] copy = new byte[content.length];
        System.arraycopy(content, 0, copy, 0, content.length);
        return copy;
    }

    public byte[] clone(final byte[] content)
    {
        if (content == null || content.length == 0)
        {
            return null;
        }
        return content.clone();
    }
}
