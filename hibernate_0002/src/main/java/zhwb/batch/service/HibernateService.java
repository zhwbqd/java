package zhwb.batch.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import zhwb.batch.dao.HibernateDAO;
import zhwb.batch.model.UserInfo;
import zhwb.batch.util.Global;
import zhwb.batch.util.StringUtils;

/**
 * @author <a href="mailto:liu.anxin13@gmail.com">Tony</a>
 */
@Service("hibernateService")
public class HibernateService {
	
	private static final Logger log = Logger.getLogger(HibernateService.class);

	@Autowired
	@Qualifier("hibernateDAO")
	private HibernateDAO<UserInfo> dao;

	public void testOcean(long num) {
		UserInfo user = null;
		
		log.info("start test Hibernate...");
		long begin = System.currentTimeMillis();
		
		for (int i = 1; i < (num + 1); i++) {
			user = new UserInfo();
			dao.save(user);
			
			user = null;
			
			if (Global.IS_BATCH) {
				if (i % Global.BATCH_NUMBER == 0) {
					dao.flush();
					dao.clear();
				}
			}
			if (i % 20000 == 0)
				System.out.printf("%s [%8d] number with a count...\n", StringUtils.getStringFromDate(new Date(), ""), i);
		}

		long end = System.currentTimeMillis();
		log.info("insert " + num + " count, consume " + (end - begin) / 1000.00000000 + " seconds");
	}
	
	public int count() {
		long begin = System.currentTimeMillis();
		int rt = dao.executeByHql("SELECT COUNT(ID) FROM UserInfo");
		
		long end = System.currentTimeMillis();
		log.info("query count, consume [" + (end - begin) / 1000.00000000 + "] seconds");
		
		return rt;
	}
	
	public void truncate() {
		long begin = System.currentTimeMillis();
		
		// 执行 native SQL
		dao.execute("TRUNCATE T_USERINFO");
		
		long end = System.currentTimeMillis();
		log.info("truncate table, consume [" + (end - begin) / 1000.00000000 + "] seconds");
	}
	
}
