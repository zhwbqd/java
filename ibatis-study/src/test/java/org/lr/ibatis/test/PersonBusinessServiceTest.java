package org.lr.ibatis.test;

import java.io.InputStream;
import java.util.ArrayList;
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

    public void testPersonBatch()
        throws Exception
    {
        InputStream fis = this.getClass().getResourceAsStream("/test.properties");
        byte[] temp = new byte[(int)fis.available()];
        fis.read(temp);
        IPersonBusinessService personService = (IPersonBusinessService)context.getBean("personBusinessService");
        //Test begin
        List<Person> plist = new ArrayList<Person>();
        Person person1 = new Person();
        person1.setId(1);
        person1.setName("jack");
        person1.setInfo("emplyee");
        person1.setInfo_blob(null);
        plist.add(person1);

        Person person2 = new Person();
        person2.setId(9999999);
        person2.setName("alex");
        person2.setInfo("emplyee");
        person2.setInfo_blob(null);
        plist.add(person2);

        Person person3 = new Person();
        person3.setId(2);
        person3.setName("mike");
        person3.setInfo("emplyee");
        person3.setInfo_blob(null);
        plist.add(person3);

        Person person4 = new Person();
        person4.setId(5);
        person4.setName("vicky");
        person4.setInfo("boss");
        person4.setInfo_blob(temp);
        plist.add(person4);

        Person person5 = new Person();
        person5.setId(6);
        person5.setName("cakin");
        person5.setInfo("boss");
        person5.setInfo_blob(null);
        plist.add(person5);

        personService.createAndUpdatePersonList(plist);
    }
}
