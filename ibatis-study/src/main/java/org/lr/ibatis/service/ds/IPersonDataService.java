package org.lr.ibatis.service.ds;

import java.util.List;

import org.lr.ibatis.bean.Person;

public interface IPersonDataService {

    List<Person> getAllPerson();

    Person getPersonById(String id);

    void updatePerson(Person person);

    void createPerson(Person person);
}
