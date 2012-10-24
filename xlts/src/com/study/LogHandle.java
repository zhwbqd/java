/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.study;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogHandle implements InvocationHandler
{
    private Object stub;

    public LogHandle(Object stub)
    {
        super();
        this.stub = stub;
    }

    public LogHandle()
    {
        super();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable
    {
        System.out.println("Do my own job before real job.....");
        Object obj = method.invoke(stub, args);
        System.out.println("Do my own job after real job.....");
        return obj;
    }

}
