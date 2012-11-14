package com.test;

//import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

//import oracle.sql.DATE;

import com.model.Employee;

public class EmployeeTest {
	public static void main(String[] args) {
		Date d=new Date();
//		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Employee e=new Employee();
		e.setName("jack");
		e.setAge(18);
		e.setBirth(d);
		
		SessionFactory sf = TestInitial.getSf();
		Session session=sf.openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		session.close();
		
	}
}
