/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.study;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest
{

    /**
     * Description goes here.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        String hello = "Hello Proxy";
        DAOImpl dao = new DAOImpl();
        InvocationHandler hdl = new LogHandle(dao);
        IDAO proxy = (IDAO)Proxy.newProxyInstance(dao.getClass().getClassLoader(), dao.getClass().getInterfaces(), hdl);
        proxy.insert(hello);
    }

}
