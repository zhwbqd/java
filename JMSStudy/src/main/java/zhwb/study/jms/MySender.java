/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class MySender { 
    public static void main(final String[] args) { 
            ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml"); 
            JmsTemplate template = (JmsTemplate) ctx.getBean("jmsTemplate"); 
            Destination destination = (Destination) ctx.getBean("destination"); 

            template.send(destination, new MessageCreator() { 
                    public Message createMessage(final Session session) throws JMSException { 
                            return session.createTextMessage("发送消息：Hello ActiveMQ Text Message！"); 
                    } 
            }); 
            System.out.println("成功发送了一条JMS消息"); 
    } 
}

