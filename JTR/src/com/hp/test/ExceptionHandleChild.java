/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.hp.test;

public class ExceptionHandleChild implements ExceptionHandleParent
{
    @Override
    public void handle()
        throws ChildException
    {
        throw new ChildException("Child Exception", new Exception());
    }
}
