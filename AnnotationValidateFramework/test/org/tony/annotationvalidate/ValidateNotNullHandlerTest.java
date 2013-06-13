package org.tony.annotationvalidate;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tony.annotationvalidate.ValidateNotNullHandler;


public class ValidateNotNullHandlerTest {
	//test object
    private AnnotationValidateModelStub filter;
    
    //test data
    String fieldName = "suppleInfo";
    
    private ValidateNotNullHandler handler;
    
	@Before
	public void setUp() throws Exception {
		filter = new AnnotationValidateModelStub();
		handler = new ValidateNotNullHandler();
	}

	@After
	public void tearDown() throws Exception {
		filter = null;
		handler = null;
	}

	@Test
	public void testValidateSuppleInfoIsNotNull() {
		filter.setSuppleInfo("test supple info");
		try{
			handler.validate(filter, filter.getClass().getDeclaredField(fieldName));
		}catch(Exception e){
			fail("There should not throw Exception.");
		}
	}

	@Test
	public void testValidateSuppleInfoIsNull() {
		try{
			handler.validate(filter, filter.getClass().getDeclaredField(fieldName));
			fail("Exception should be thrown.");
		}catch(Exception ex){
			assertEquals("This value should not null.But the value of suppleInfo is Null.",ex.getMessage());
		}
	}

}
