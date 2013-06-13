package org.tony.annotationvalidate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tony.annotationvalidate.ValidateNotEmptyHandler;


public class ValidateNotEmptyHandlerTest {

	//test object
    private AnnotationValidateModelStub filter;
    
    private ValidateNotEmptyHandler handler;
    
    String fieldName = "moList";
    
	@Before
	public void setUp() throws Exception {
		filter = new AnnotationValidateModelStub();
		handler = new ValidateNotEmptyHandler();
	}

	@After
	public void tearDown() throws Exception {
		filter = null;
		handler = null;
	}

	@Test
	public void testValidateCollectionNotEmpty() {
		List<Object> moLists = new ArrayList<Object>();
		for(int i = 0; i < 3; i++){
			Object mo = new Object();
			moLists.add(mo);
		}
		filter.setMoList(moLists);
		try{
			handler.validate(filter, filter.getClass().getDeclaredField(fieldName));
		}catch(Exception e){
			fail("There should not throw Exception.");
		}
	}
	
	@Test
	public void testValidateCollectionEmpty() {
		List<Object> moLists = new ArrayList<Object>();
		filter.setMoList(moLists);
		try{
			handler.validate(filter, filter.getClass().getDeclaredField(fieldName));
			fail("Exception should be thrown.");
		}catch(Exception ex){
			assertEquals("This collection should not to be empty.The collection of "+fieldName+" size is:0",ex.getMessage());
		}
	}

}
