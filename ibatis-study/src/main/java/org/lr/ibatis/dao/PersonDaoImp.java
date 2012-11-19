package org.lr.ibatis.dao;

import java.util.List;

import org.lr.ibatis.bean.Person;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class PersonDaoImp extends SqlMapClientDaoSupport implements PersonDao {

	@SuppressWarnings("unchecked")
	public List<Person> getAllPerson() {
		return getSqlMapClientTemplate().queryForList("queryAll");
	}

	public Person getPersonById(String id ) {
		return (Person)getSqlMapClientTemplate().queryForObject("queryById",id);
	}

    public void updatePerson(Person person)
    {
        getSqlMapClientTemplate().update("updatePerson", person);
	}

    public void insertPerson(Person person)
    {
        getSqlMapClientTemplate().insert("insertPerson", person);
    }

}
