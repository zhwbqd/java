/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package org.lr.ibatis.service.bs;

import java.util.List;

import org.lr.ibatis.bean.Person;
import org.lr.ibatis.service.ds.IPersonDataService;

public class PersonBusinessService implements IPersonBusinessService
{

    private IPersonDataService personDs;

    public List<Person> findAllPerson()
    {
        return personDs.getAllPerson();
    }

    public Person findPersonById(String id)
    {
        return personDs.getPersonById(id);
    }

    public void createOrUpdatePerson(Person person)
    {
        Person rsPerson = personDs.getPersonById(String.valueOf(person.getId()));
        if (rsPerson == null)
        {
            personDs.createPerson(person);
        }
        else
        {
            personDs.updatePerson(person);
        }
    }

    public IPersonDataService getPersonDs()
    {
        return personDs;
    }

    public void setPersonDs(IPersonDataService personDs)
    {
        this.personDs = personDs;
    }

    public void createAndUpdatePersonList(List<Person> persons)
    {
        this.personDs.createAndUpdatePersonList(persons);
    }

}
