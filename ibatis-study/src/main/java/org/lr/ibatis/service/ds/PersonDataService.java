package org.lr.ibatis.service.ds;

import java.util.List;

import org.lr.ibatis.bean.Person;
import org.lr.ibatis.dao.PersonDao;

public class PersonDataService implements IPersonDataService {
	
	private PersonDao personDao;
	
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public List<Person> getAllPerson() {
		return personDao.getAllPerson();
	}

	public Person getPersonById(String id) {
		return personDao.getPersonById(id);
	}

    public void updatePerson(Person person)
    {
        personDao.updatePerson(person);
	}

    public void createPerson(Person person)
    {
        personDao.insertPerson(person);
    }
}
