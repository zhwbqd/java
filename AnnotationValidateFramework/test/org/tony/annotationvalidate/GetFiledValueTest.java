/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tony.annotationvalidate;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tony.annotationvalidate.GetFiledValue;


/**
 *
 * @author jiangtch
 */
public class GetFiledValueTest {

    //test object
    private AnnotationValidateModelStub filter;

    public GetFiledValueTest() {
    }

    @Before
    public void setUp() {
        filter = new AnnotationValidateModelStub();
    }

    @After
    public void tearDown() {
        filter = null;
    }

    /**
     * Test of getField method, of class GetFiledValue.
     */
    @Test
    public void testGetField() throws Exception {
        filter.setSuppleInfo("test supple info");
        String result = (String)GetFiledValue.getField(filter, "suppleInfo");
        assertEquals(filter.getSuppleInfo(), result);
    }

}