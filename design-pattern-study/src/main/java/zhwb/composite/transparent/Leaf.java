/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.composite.transparent;

import java.util.Enumeration;

public class Leaf implements Component
{

    public void sampleOperation()
    {
        System.out.println("Do your business here");
    }

    public Composite getComposite()
    {
        return null;
    }

    public void add(Component component)
    {
    }

    public void remove(Component component)
    {
    }

    public Enumeration<Component> conponents()
    {
        return null;
    }

}
