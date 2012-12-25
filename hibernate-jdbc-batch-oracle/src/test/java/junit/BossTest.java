package junit;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.model.Boss;


public class BossTest {
	private static SessionFactory sf=null;
	@BeforeClass
	public static void beforeClass(){
		sf=new AnnotationConfiguration().configure().buildSessionFactory();
	}
@Test
public void testBossSave()
{
		Date d=new Date();

		Boss b=new Boss();
		b.setId(0);
		b.setName("jack");
		b.setAge(18);
		b.setBirth(d);
		
		System.out.println("--------------"+b.getId());
		Session session=sf.getCurrentSession();
		session.beginTransaction();
		session.save(b);
		session.getTransaction().commit();
		System.out.println("--------------"+b.getId());
	}
	@AfterClass
	public static void afterClass(){
		
		
		
	}
}