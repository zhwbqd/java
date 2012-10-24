/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.spring.annotation;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SimpleThrowingAspect
{
    @Pointcut("execution(* study.spring.IDAO.*(..))")
    public void simpleDaoPointcut()
    {
    }

    //后置通知
    @AfterReturning(returning = "retVal", pointcut = "simpleDaoPointcut()")
    public void simpleAdviceAfterReturning(Object retVal)
    {
        System.out.println("get the retVAL is " + retVal + " I will commit into database!");
    }

    //最终通知 注意:如果出现异常则前置通知和最终通知执行，后置通知则不执行  
    @After(value = "simpleDaoPointcut()")
    public void simpleAdviceAfter()
    {
        System.out.println("After Metod Returned, I will close the connection!");

    }

    //前置通知
    @Before(value = "simpleDaoPointcut() &&" + "args(obj,..)")
    public void simpleAdviceBefore(Object obj)
    {
        if (obj == null)
        {
//            throw new RuntimeException(new NullPointerException());
        }
        else
        {
            System.out.println("I will check the data integrity! input Param: " + obj.toString());
        }

    }

    @AfterThrowing(pointcut = "simpleDaoPointcut()", throwing = "ex")
    //定义异常通知  
    public void doExceptionAction(Exception ex)
    {
        System.out.println("When Exception throw, it will rollback current transaction" + ex.getStackTrace());
    }
}
