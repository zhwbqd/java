package org.lr.ibatis.test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

import org.lr.ibatis.bean.Person;

public class JdbcTest extends TestCase {

	public void testJdbc() throws Exception {
		long start = System.currentTimeMillis();
		Class.forName("com.mysql.jdbc.Driver");
		for( int i = 1 ; i < 10 ; i++){
		java.sql.Connection conn = DriverManager.getConnection(
"jdbc:mysql://localhost:3306/test", "root", "root");
		Statement sta = conn.createStatement();
		ResultSet rs = sta.executeQuery("select * from person");
		List<Person> list = new LinkedList<Person>();
		while (rs.next()) {
			Person p = new Person();
                p.setId(rs.getInt("id"));
			p.setName(rs.getString("name"));
			p.setInfo(rs.getString("info"));
			list.add(p);
		}
		long end = System.currentTimeMillis();
            System.out.println("Time: " + (end - start));
        }
//		for (Person item : list) {
//			System.out.println(item.getId());
//			System.out.println(item.getName());
//			System.out.println(item.getInfo());
//
//		}
	}
}
