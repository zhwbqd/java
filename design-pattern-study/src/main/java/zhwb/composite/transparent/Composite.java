/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.composite.transparent;

import java.util.Enumeration;
import java.util.Vector;

public class Composite implements Component
{

    private Vector<Component> componentVector = new Vector<Component>();

    public void sampleOperation()
    {
        Enumeration<Component> enumeration = conponents();
        while (enumeration.hasMoreElements())
        {
            enumeration.nextElement().sampleOperation();
        }
    }

    public Composite getComposite()
    {
        return this;
    }

    public void add(Component component)
    {
        componentVector.add(component);

    }

    public void remove(Component component)
    {
        componentVector.remove(component);

    }

    public Enumeration<Component> conponents()
    {
        return componentVector.elements();
    }

}
