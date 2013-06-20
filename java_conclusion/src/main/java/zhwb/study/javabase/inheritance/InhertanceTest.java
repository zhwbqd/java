/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.javabase.inheritance;

public class InhertanceTest
{
    public static void main(final String[] args)
    {
        Parent p = getChild();
        System.out.println(p);
        Child c = (Child)getParent();
        System.out.println(c);

    }

    private static Parent getParent()
    {
        Parent p = new Parent();
        p.setHomeLocation("Parent");
        p.setHomeName("Parent");
        return p;
    }

    private static Child getChild()
    {
        Child c = new Child();
        c.setHomeLocation("Child");
        c.setHomeName("Child");
        c.setChildSelfName("Jack");
        return c;
    }
}
