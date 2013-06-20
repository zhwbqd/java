/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.javabase.inheritance;

public class Child extends Parent
{
    private String childSelfName;

    /**
     * Get the property childSelfName
     *
     * @return the childSelfName
     */
    public final String getChildSelfName()
    {
        return childSelfName;
    }

    /**
     * Set the property childSelfName
     *
     * @param childSelfName the childSelfName to set
     */
    public final void setChildSelfName(final String childSelfName)
    {
        this.childSelfName = childSelfName;
    }

    /** {@inheritDoc}
     *  @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("Child [childSelfName=%s]", childSelfName,super.getHomeLocation());
    }
}
