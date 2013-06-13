package org.tony.annotationvalidate;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tony.annotationvalidate.ValidateIntHandler;


public class ValidateIntHandlerTest {

	//test object
    private AnnotationValidateModelStub filter;
    
    //test data
    String fieldName = "maxRows";
    
    private ValidateIntHandler handler;
    
	@Before
	public void setUp() throws Exception {
		filter = new AnnotationValidateModelStub();
		handler = new ValidateIntHandler();
	}

	@After
	public void tearDown() throws Exception {
		filter = null;
		handler = null;
	}

	@Test
	public void testValidateMaxRowsIsCorrect() {
		filter.setMaxRows(10);
		try{
			handler.validate(filter, filter.getClass().getDeclaredField(fieldName));
		}catch(Exception e){
			fail("There should not throw Exception.");
		}
	}

	@Test
	public void testValidateMaxRowsIsNotCorrect() {
		Integer value = -10;
		filter.setMaxRows(value);
		try{
			handler.validate(filter, filter.getClass().getDeclaredField(fieldName));
			fail("Exception should be thrown.");
		}catch(Exception ex){
			assertEquals("Value of the integer is not in expected scope.The value is:" + value+",The min value should is:1",ex.getMessage());
		}
	}
}
