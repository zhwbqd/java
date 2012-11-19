package org.lr.ibatis.test;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

import org.lr.ibatis.bean.Person;
import org.lr.ibatis.service.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonServiceTest extends TestCase {

	private ApplicationContext context;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		super.setUp();
	}

	public void testPersonService() {
		long start = System.currentTimeMillis();
		PersonService personService = (PersonService) context
				.getBean("personService");
		List<Person> list = new LinkedList<Person>();
		list = personService.getAllPerson();
		long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start));
		for (Person p : list) {
			System.out.println(p.getId());
			System.out.println(p.getName());
			System.out.println(p.getInfo());
			System.out.println(new String(p.getInfo_blob()));

		}
	}

	public void testPersonUpdate() throws Exception{
        InputStream fis = this.getClass().getResourceAsStream("/test.properties");
		byte[] temp = new byte[(int)fis.available()];
		fis.read(temp);
		PersonService personService = (PersonService) context.getBean("personService");
		Person person = new Person();
        person.setId(9999999);
        person.setName("jack");
        person.setInfo("emplyee");
		person.setInfo_blob(temp);
		personService.updateBlob(person);
	}
}
