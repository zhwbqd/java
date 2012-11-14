package com.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.service.HibernateService;
import com.util.Global;

/**
 * @author <a href="mailto:liu.anxin13@gmail.com">Tony</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestHibernate {

	private static final Logger log = Logger.getLogger(TestHibernate.class);

	@Autowired
	@Qualifier("hibernateService")
	private HibernateService hibernate;

	@Test
	public void testHibernate() {
		hibernate.testOcean(Global.NUM);
		
		System.err.printf("see momory! have a quick please...\n");
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			log.error("exception: " + e.getMessage());
		}
	}

	// @Test
	public void testCount() {
		log.info("count: " + hibernate.count());
	}
	
	// @Test
	public void truncate() {
		hibernate.truncate();
	}

}
