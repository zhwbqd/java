package study.spring;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.annotation.SelfDefinedException;

/**
 * Unit test for simple App.
 */
public class AOPAnnotationTest extends TestCase
{
    ApplicationContext context = null;

    @Override
    protected void setUp()
        throws Exception
    {
        super.setUp();
        this.context = new ClassPathXmlApplicationContext("/annotation-beans.xml");
    }

    public void testAOP()
        throws SelfDefinedException
    {

        IDAO dao = (IDAO)context.getBean("daoImpl");
        dao.insert("sth");
        dao.find("sth");
        try
        {
            dao.find(null);
        }
        catch (Exception ex)
        {
            assertTrue(ex instanceof RuntimeException);
        }

    }
}
