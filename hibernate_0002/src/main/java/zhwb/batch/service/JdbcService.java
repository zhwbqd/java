package zhwb.batch.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import zhwb.batch.dao.JdbcDAO;
import zhwb.batch.model.UserInfo;
import zhwb.batch.util.Global;
import zhwb.batch.util.StringUtils;

/**
 * @author <a href="mailto:liu.anxin13@gmail.com">Tony</a>
 */
@Service("jdbcService")
public class JdbcService {
	
	private static final Logger log = Logger.getLogger(JdbcService.class);
	
	@Autowired
	@Qualifier("jdbcDAO")
	private JdbcDAO dao;

	/**
	 * 使用 addbatch 的方式进行海量插入
	 * 
	 * @param num
	 */
	public void testOcean(long num) {
		// 使用 spring 获取连接
		Connection conn = dao.getConn();
		
		try {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO T_USERINFO(CREATE_TIME, ID) VALUES(?, ?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			UserInfo user = null;
			
			log.info("start test jdbc...");
			long begin = System.currentTimeMillis();
			
			for (int i = 1; i < (num + 1); i++) {
				// 要保证公平, 也在循环中 new 对象
				user = new UserInfo();
				pstm.setTimestamp(1, user.getCreateTime());
				pstm.setString(2, user.getId());
				pstm.addBatch();
				
				user = null;
				
				// 批处理
				if (Global.IS_BATCH) {
					if (i % Global.BATCH_NUMBER == 0) {
						pstm.executeBatch();
						conn.commit();
						pstm.clearBatch();
					}
				}
				if (i % 20000 == 0)
					System.out.printf("%s [%8d] number with a count...\n", 
							StringUtils.getStringFromDate(new Date(), ""), i);
			}
			pstm.executeBatch();
			conn.commit();
			pstm.clearBatch();

			long end = System.currentTimeMillis();
			log.info("insert " + num + " count, consume " + (end - begin) / 1000.00000000 + " seconds");
			
		} catch (Exception e) {
			log.error("exception: " + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				conn = null;
			}
		}
	}
	
	/**
	 * 使用拼接字符串的方式进行海量插入
	 * 
	 * @param num
	 */
	public void testOceanWithSplit(long num) {
		Connection conn = dao.getConn();
		
		try {
			conn.setAutoCommit(false);
			Statement st = conn.createStatement();
			
			StringBuilder sql = new StringBuilder();
			String str = "INSERT INTO T_USERINFO(CREATE_TIME, ID) VALUES ";
			UserInfo user = null;
			
			log.info("start test jdbc with split...");
			long begin = System.currentTimeMillis();
			for (int i = 1; i < (num + 1); i++) {
				// 要保证公平, 也在循环中 new 对象
				user = new UserInfo();
				
				sql.append(str);
				sql.append("('").append(user.getCreateTime());
				sql.append("', '");
				sql.append(user.getId()).append("');");
				user = null;
				
				// 批处理
				if (Global.IS_BATCH) {
					if (i % Global.BATCH_NUMBER == 0) {
						// 执行并提交至数据库
						st.execute(sql.deleteCharAt(sql.length() - 1).toString());
						conn.commit();
						
						// 重新开始拼接字符串
						sql.delete(str.length(), sql.length());
					}
				}
				if (i % 20000 == 0)
					System.out.printf("%s [%8d] number with a count...\n", 
							StringUtils.getStringFromDate(new Date(), ""), i);
			}
			// 如果 总数据量不能整除批量数则将余下的数据进行执行
			if (num % Global.BATCH_NUMBER != 0) {
				st.execute(sql.deleteCharAt(sql.length() - 1).toString());
				conn.commit();
			}
			
			long end = System.currentTimeMillis();
			log.info("insert " + num + " count, consume " + (end - begin) / 1000.00000000 + " seconds");
			
		} catch (Exception e) {
			log.error("exception: " + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				conn = null;
			}
		}
	}
	
	/**
	 * 查询数据量
	 * 
	 * @return
	 */
	public int count() {
		int rt = 0;
		Connection conn = dao.getConn();
		long begin = System.currentTimeMillis();
		String sql = "SELECT COUNT(*) FROM T_USERINFO";
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			rs.next();
			rt = rs.getInt(1);
		} catch (Exception e) {
			log.error("exception: " + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				conn = null;
			}
		}
		long end = System.currentTimeMillis();
		log.info("query count, consume [" + (end - begin) / 1000.00000000 + "] seconds");
		return rt;
	}
	
	/**
	 * 清表
	 */
	public void truncate() {
		// 使用直连获取连接.
		Connection conn = dao.getConnection();
		
		String sql = "TRUNCATE T_USERINFO";
		long begin = System.currentTimeMillis();
		
		try {
			conn.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			log.error("exception : " + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				conn = null;
			}
		}
		
		long end = System.currentTimeMillis();
		log.info("truncate table, consume [" + (end - begin) / 1000.00000000 + "] seconds");
	}

}
