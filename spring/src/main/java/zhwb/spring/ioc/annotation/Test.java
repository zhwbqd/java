/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.spring.ioc.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test
{
    public static void main(final String[] args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("annotation-ioc-beans.xml");

        NotificationController con = (NotificationController)context.getBean("notificationController");

        System.out.println(con.sayHelloWorld());
    }
}
