package org.lr.ibatis.service.ds;

import java.util.ArrayList;
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

    public void createAndUpdatePersonList(List<Person> persons)
    {
        final List<Person> insertList = new ArrayList<Person>();
        final List<Person> updateList = new ArrayList<Person>();
        List<Person> allPersons = this.getAllPerson();

        for (Person p1 : persons)
        {
            for (Person p2 : allPersons)
            {
                if (p1.getId() == p2.getId())
                {
                    updateList.add(p1);
                    break;
                }
            }
        }

        for (Person p1 : persons)
        {
            if (!updateList.contains(p1))
            {
                insertList.add(p1);
            }
        }
        personDao.batchUpdate(updateList);
        personDao.batchInsert(insertList);
    }
}
