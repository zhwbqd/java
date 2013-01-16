package study.spring;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import zhwb.aop.mockbusiness.IDAO;


/**
 * Unit test for simple App.
 */
public class AOPProxyProgramTest extends TestCase
{
    ApplicationContext context = null;

    @Override
    protected void setUp()
        throws Exception
    {
        super.setUp();
        this.context = new ClassPathXmlApplicationContext("/program-beans.xml");
    }

    public void testAOP()
    {
        @SuppressWarnings("unchecked")
        IDAO<String> dao = (IDAO<String>)context.getBean("proxy");
        dao.insert("sth");
        dao.find("sth");
        dao.delete("sth");
    }
}
