/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.hp.test;

public class TestHandle
{
public static void main(String[] args)
{
    ExceptionHandleParent test = new ExceptionHandleChild();
    try
    {
        test.handle();
    }
    catch (ParentException e)
    {
            System.out.println(e.getMessage());
    }
}
}
