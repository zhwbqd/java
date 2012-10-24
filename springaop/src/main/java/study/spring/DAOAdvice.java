/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package study.spring;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class DAOAdvice implements AfterReturningAdvice, MethodBeforeAdvice, MethodInterceptor
{

    @Override
    public Object invoke(MethodInvocation invocation)
        throws Throwable
    {
        Object obj = invocation.proceed();
        System.out.println("I will do something in the method");
        return obj;
    }

    @Override
    public void before(Method method, Object[] args, Object target)
        throws Throwable
    {
        if (method.getName().equals("insert"))
        {
            System.out.println("I will do something before the insert method");
            method.invoke(target, args);
        }
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
        throws Throwable
    {
        if (method.getName().equals("delete"))
        {
            System.out.println("I will do something after the method");
        }
    }

}
