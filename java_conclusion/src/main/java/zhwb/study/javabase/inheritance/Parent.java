/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.javabase.inheritance;

public class Parent
{
    private String homeLocation;

    private String homeName;

    /**
     * Get the property homeLocation
     *
     * @return the homeLocation
     */
    public final String getHomeLocation()
    {
        return homeLocation;
    }

    /**
     * Set the property homeLocation
     *
     * @param homeLocation the homeLocation to set
     */
    public final void setHomeLocation(final String homeLocation)
    {
        this.homeLocation = homeLocation;
    }

    /**
     * Get the property homeName
     *
     * @return the homeName
     */
    public final String getHomeName()
    {
        return homeName;
    }

    /**
     * Set the property homeName
     *
     * @param homeName the homeName to set
     */
    public final void setHomeName(final String homeName)
    {
        this.homeName = homeName;
    }

    /** {@inheritDoc}
     *  @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("Parent [homeLocation=%s, homeName=%s]", homeLocation, homeName);
    }

}
