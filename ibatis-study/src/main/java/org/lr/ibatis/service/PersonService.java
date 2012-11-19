package org.lr.ibatis.service;

import java.util.List;

import org.lr.ibatis.bean.Person;

public interface PersonService {
	public List<Person> getAllPerson();
	public Person getPersonById(String id);
	public void updateBlob(Person person);
}
