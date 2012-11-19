package org.lr.ibatis.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.TestCase;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataSourceTest extends TestCase {

	public void testDataSource() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		BasicDataSource dataSource = (BasicDataSource)context.getBean("dataSource");
		try {
			Connection conn = dataSource.getConnection();
			Statement sta = conn.createStatement();
			ResultSet rs = sta.executeQuery("select * from person");
			while(rs.next()){
				System.out.println(rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
