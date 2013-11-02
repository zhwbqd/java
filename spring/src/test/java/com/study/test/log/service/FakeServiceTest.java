package com.study.test.log.service;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import zhwb.study.aopschema.log.util.FakeService;



/**
 * Unit test for simple App.
 */
public class FakeServiceTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
	public FakeServiceTest(final String testName)
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
		return new TestSuite(FakeServiceTest.class);
    }


	public void testFakeServiceTest()
    {
        ApplicationContext ac = new ClassPathXmlApplicationContext("schema-beans.xml");
		FakeService fakeService = (FakeService) ac.getBean("fakeService");
		fakeService.execute("sth");
    }
}
