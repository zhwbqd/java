package zhwb.batch.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import zhwb.batch.service.JdbcService;
import zhwb.batch.util.Global;

/**
 * @author <a href="mailto:liu.anxin13@gmail.com">Tony</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestJdbc {
	
	private static final Logger log = Logger.getLogger(TestJdbc.class);
	
	@Autowired
	@Qualifier("jdbcService")
	private JdbcService jdbc;
	
	// @Test
	public void testJdbcWithSplit() {
		jdbc.testOceanWithSplit(Global.NUM);
		
		nothing();
	}
	
	@Test
	public void testJdbc() {
		jdbc.testOcean(Global.NUM);
		
		nothing();
	}
	
	// @Test
	public void truncateJdbc() {
		jdbc.truncate();
	}
	
	public void nothing() {
		System.err.printf("see momory! have a quick please...\n");
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			log.error("exception: " + e.getMessage());
		}
	}
	
	// @Test
	public void testCount() {
		int count = jdbc.count();
		log.info("count: " + count);
	}

}
