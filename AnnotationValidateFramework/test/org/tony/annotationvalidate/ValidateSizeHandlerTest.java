package org.tony.annotationvalidate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tony.annotationvalidate.ValidateSizeHandler;


public class ValidateSizeHandlerTest {

	//test object
    private AnnotationValidateModelStub filter;
    
    private ValidateSizeHandler handler;
    
    //test data
    String fieldName = "alarmNumber";
    
	@Before
	public void setUp() throws Exception {
		filter = new AnnotationValidateModelStub();
		handler = new ValidateSizeHandler();
	}

	@After
	public void tearDown() throws Exception {
		filter = null;
		handler = null;
	}
	
	@Test
	public void testValidateSizeIsCorrect() {
		filter.setAlarmNumber("123456");
		try{
			handler.validate(filter, filter.getClass().getDeclaredField(fieldName));
		}catch(Exception e){
			fail("There should not throw Exception.");
		}
	}

	@Test
	public void testValidateSizeIsNotCorrect() {
		String value = "1234567890";
		filter.setAlarmNumber(value);
		try{
			handler.validate(filter, filter.getClass().getDeclaredField(fieldName));
			fail("Exception should be thrown.");
		}catch(Exception ex){
			assertEquals("This value is not valid format.The size is:"+ value.length()+",The min size should is:0,the max size should is:9",ex.getMessage());
		}
	}

}
