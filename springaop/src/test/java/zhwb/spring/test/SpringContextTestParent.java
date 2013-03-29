package zhwb.spring.test;

import java.util.TimeZone;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The Class SpringContextTestParent.
 */
@ContextConfiguration(locations = {"/datasources.xml", "/ioc-h2.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class SpringContextTestParent {

    /**
     * Inits the TestParent.
     */
	@BeforeClass
	public static void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
}
