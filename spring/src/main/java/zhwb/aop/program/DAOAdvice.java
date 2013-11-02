/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.aop.program;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class DAOAdvice implements AfterReturningAdvice, MethodBeforeAdvice, MethodInterceptor
{
public Object invoke(final MethodInvocation invocation)
        throws Throwable
    {
        if (invocation.getMethod().getName().equals("find")) //在FIND方法时做事情
        {
            System.out.println("I will do something in the find method");
    }
        return invocation.proceed();
    }

    public void before(final Method method, final Object[] args, final Object target)
        throws Throwable
    {
        if (method.getName().equals("insert")) //在INSERT方法时做事情
        {
            System.out.println("I will do something before the insert method");
        }
    }

    public void afterReturning(final Object returnValue, final Method method, final Object[] args, final Object target)
        throws Throwable
    {
        if (method.getName().equals("delete")) //在DELETE方法时做事情
        {
            System.out.println("I will do something after delete method");
        }
    }

}
