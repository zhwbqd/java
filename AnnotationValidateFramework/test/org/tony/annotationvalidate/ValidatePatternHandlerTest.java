package org.tony.annotationvalidate;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tony.annotationvalidate.ValidatePatternHandler;


public class ValidatePatternHandlerTest {

	//test object
    private AnnotationValidateModelStub filter;
    
    //test data
    String fieldName = "gid";
    
    private ValidatePatternHandler handler;
    
	@Before
	public void setUp() throws Exception {
		filter = new AnnotationValidateModelStub();
		handler = new ValidatePatternHandler();
	}

	@After
	public void tearDown() throws Exception {
		filter = null;
		handler = null;
	}

	@Test
	public void testValidateGidMatchThePattern() {
		filter.setGid("123456");
		try{
			handler.validate(filter, filter.getClass().getDeclaredField(fieldName));
		}catch(Exception e){
			fail("There should not throw Exception.");
		}
	}
	
	@Test
	public void testValidateGidNotMatchThePattern() {
		String value = "123abc";
		filter.setGid(value);
		try{
			handler.validate(filter, filter.getClass().getDeclaredField(fieldName));
			fail("Exception should be thrown.");
		}catch(Exception ex){
			assertEquals("This value is not valid format.The value is:" + value,ex.getMessage());
		}
	}

}
