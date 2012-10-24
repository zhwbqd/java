import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

public class TestEmployee
{

    /**
     * Description goes here.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        Employee emp = (Employee)ctx.getBean("employee");
        System.out.println(emp.getManagerNm());
    }

}
