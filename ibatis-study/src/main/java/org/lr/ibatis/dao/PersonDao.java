package org.lr.ibatis.dao;

import java.util.List;

import org.lr.ibatis.bean.Person;

public interface PersonDao {
    List<Person> getAllPerson();

    Person getPersonById(String id);

    void updatePerson(Person person);

    void insertPerson(Person person);

    void batchUpdate(final List<Person> updateList);

    void batchInsert(final List<Person> insertList);
}
