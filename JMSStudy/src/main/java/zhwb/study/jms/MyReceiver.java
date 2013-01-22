/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class MyReceiver { 
    public static void main(String[] args) throws JMSException { 
            ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml"); 
            JmsTemplate template = (JmsTemplate) ctx.getBean("jmsTemplate"); 
            Destination destination = (Destination) ctx.getBean("destination"); 
            while (true) { 
                    TextMessage txtmsg = (TextMessage) template.receive(destination); 
                    if (null != txtmsg) 
                            System.out.println("收到消息内容为: " + txtmsg.getText()); 
                    else 
                            break; 
            } 
    } 
}

