package org.lr.ibatis.service;

import java.util.List;

import org.lr.ibatis.bean.Person;

public interface PersonService {

    List<Person> getAllPerson();

    Person getPersonById(String id);

    void updateBlob(Person person);

    void createPerson(Person person);
}
