package org.lr.ibatis.service;

import java.util.List;

import org.lr.ibatis.bean.Person;
import org.lr.ibatis.dao.PersonDao;

public class PersonServiceImp implements PersonService {
	
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

	public void updateBlob(Person person) {
		personDao.updateBlob(person);
	}

}
