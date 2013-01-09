/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.composite.safety;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Composite 安全方式实现合成模式， AWT库中实现方式
 */
public class Composite implements Component
{

    /** The component list. */
    private List<Component> componentList = new ArrayList<Component>();

    /* (non-Javadoc)
     * @see zhwb.composite.safety.Component#sampleOperation()
     */
    public void sampleOperation()
    {
        for (Component c : componentList)
        {
            c.sampleOperation();
        }

    }

    /**
     * Adds the component.
     *
     * @param c the c
     */
    public void addComponent(Component c)
    {
        componentList.add(c);
    }

    /**
     * Removes the component.
     *
     * @param c the c
     */
    public void removeComponent(Component c)
    {
        componentList.remove(c);
    }

    /**
     * Gets the all components.
     *
     * @return the all components
     */
    public List<Component> getAllComponents()
    {
        return componentList;

    }

}
