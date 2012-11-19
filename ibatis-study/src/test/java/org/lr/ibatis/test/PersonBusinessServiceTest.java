package org.lr.ibatis.test;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

import org.lr.ibatis.bean.Person;
import org.lr.ibatis.service.bs.IPersonBusinessService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonBusinessServiceTest extends TestCase {

	private ApplicationContext context;

	@Override
	protected void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		super.setUp();
	}

	public void testPersonService() {
		long start = System.currentTimeMillis();
        IPersonBusinessService personService = (IPersonBusinessService)context
.getBean("personBusinessService");
		List<Person> list = new LinkedList<Person>();
        list = personService.findAllPerson();
		long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start));
		for (Person p : list) {
			System.out.println(p.getId());
			System.out.println(p.getName());
			System.out.println(p.getInfo());
            System.out.println(p.getInfo_blob() == null ? "" : new String(p.getInfo_blob()));

		}
	}

	public void testPersonUpdate() throws Exception{
        InputStream fis = this.getClass().getResourceAsStream("/test.properties");
		byte[] temp = new byte[(int)fis.available()];
		fis.read(temp);
        IPersonBusinessService personService = (IPersonBusinessService)context.getBean("personBusinessService");
		Person person = new Person();
        person.setId(9999999);
        person.setName("jack");
        person.setInfo("emplyee");
		person.setInfo_blob(temp);
        personService.createOrUpdatePerson(person);
	}

    public void testPersonInsert()
        throws Exception
    {
        InputStream fis = this.getClass().getResourceAsStream("/test.properties");
        byte[] temp = new byte[(int)fis.available()];
        fis.read(temp);
        Person person = new Person();
        person.setId(4);
        person.setName("jack");
        person.setInfo("emplyee");
        person.setInfo_blob(temp);
        IPersonBusinessService personService = (IPersonBusinessService)context.getBean("personBusinessService");
        personService.createOrUpdatePerson(person);
    }
}
