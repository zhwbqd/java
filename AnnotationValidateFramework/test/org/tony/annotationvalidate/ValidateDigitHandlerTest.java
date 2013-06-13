package org.tony.annotationvalidate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tony.annotationvalidate.ValidateDigitHandler;


public class ValidateDigitHandlerTest {
	//test object
    private AnnotationValidateModelStub filter;
    
    //test data
    String fieldName = "alarmNumber";
    
    private ValidateDigitHandler handler;

	@Before
	public void setUp() throws Exception {
		filter = new AnnotationValidateModelStub();
		handler = new ValidateDigitHandler();
	}

	@After
	public void tearDown() throws Exception {
		filter = null;
		handler = null;
	}

	@Test
	public void testValidateIsDigit() {
		filter.setAlarmNumber("123456");
		try{
			handler.validate(filter, filter.getClass().getDeclaredField(fieldName));
		}catch(Exception e){
			fail("There should not throw Exception.");
		}
	}
	
	@Test
	public void testValidateIsEmpty() {
		filter.setAlarmNumber("");
		try{
			handler.validate(filter, filter.getClass().getDeclaredField(fieldName));
		}catch(Exception e){
			fail("There should not throw Exception.");
		}
	}
	
	@Test
	public void testValidateIsNotDigit() {
		String value = "123abc";
		filter.setAlarmNumber(value);
		try{
			handler.validate(filter, filter.getClass().getDeclaredField(fieldName));
			fail("Exception should be thrown.");
		}catch(Exception ex){
			assertEquals("The value should be digit only.The value is:" + value,ex.getMessage());
		}
	}

}
