package com.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:liu.anxin13@gmail.com">Tony</a>
 */
@Repository("jdbcDAO")
public class JdbcDAO {
	
	private static final Logger log = Logger.getLogger(JdbcDAO.class);
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate template;
	
	public Connection getConn() {
		try {
			return template.getDataSource().getConnection();
		} catch (Exception e) {
			log.info("获取连接时异常: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	// 如果感觉使用 spring 获取的 Connection 太慢, 直接使用下面的直连...
	
	private static Properties p = new Properties();
	
	static {
		try {
			p.load(JdbcDAO.class.getClassLoader().getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
		}
	}
	
	public Connection getConnection() {
		try {
			String driver = p.getProperty("jdbc.driver");
			String url = p.getProperty("jdbc.url");
			String user = p.getProperty("jdbc.username");
			String password = p.getProperty("jdbc.password");
			
			Class.forName(driver);
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			log.error("connection exception: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

}