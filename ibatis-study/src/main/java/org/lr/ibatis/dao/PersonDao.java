package org.lr.ibatis.dao;

import java.util.List;

import org.lr.ibatis.bean.Person;

public interface PersonDao {
	public List<Person> getAllPerson();
	public Person getPersonById(String id);
	public void updateBlob(Person person);
}
