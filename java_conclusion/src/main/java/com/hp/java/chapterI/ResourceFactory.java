/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.hp.java.chapterI;

public class ResourceFactory
{
    private static class ResourceHolder
    {
        public static Resource resource = new Resource();
    }

    public static Resource getResource()
    {
        return ResourceFactory.ResourceHolder.resource;
    }

    static class Resource
    {
    }
}
