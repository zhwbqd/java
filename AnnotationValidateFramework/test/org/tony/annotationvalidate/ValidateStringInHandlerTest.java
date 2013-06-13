package org.tony.annotationvalidate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tony.annotationvalidate.ValidateStringInHandler;


public class ValidateStringInHandlerTest {

	//test object
	private AnnotationValidateModelStub filter;
	
	private ValidateStringInHandler handler;
	
	//test data
	String fieldName = "alarmType";
	
	@Before
	public void setUp() throws Exception {
		filter = new AnnotationValidateModelStub();
		handler = new ValidateStringInHandler();
	}

	@After
	public void tearDown() throws Exception {
		filter = null;
		handler = null;
	}

	@Test
	public void testValidateStringIsInTheGivenValue() {
		filter.setAlarmType("Communication");
		try{
			handler.validate(filter, filter.getClass().getDeclaredField(fieldName));
		}catch(Exception e){
			fail("There should not throw Exception.");
		}
	}

	@Test
	public void testValidateStringIsNotInTheGivenValue() {
		String value = "test";
		filter.setAlarmType(value);
		try{
			handler.validate(filter, filter.getClass().getDeclaredField(fieldName));
			fail("Exception should be thrown.");
		}catch(Exception ex){
			assertEquals("The value is not expected.this field value is:"+value+",the excepted value is:[Communication, Environment, Equipment, Processing, Quality of Service, All]",ex.getMessage());
		}
	}
}
