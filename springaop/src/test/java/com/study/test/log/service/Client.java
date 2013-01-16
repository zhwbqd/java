package com.study.test.log.service;


import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import zhwb.study.aopschema.log.service.UserManager;

public class Client extends TestCase {

	public void testAop() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("schema-beans.xml");
		UserManager userManager = (UserManager) ac.getBean("userManager");
		userManager.add("zhangshang", "123456");
		userManager.del("23");
	}
}

