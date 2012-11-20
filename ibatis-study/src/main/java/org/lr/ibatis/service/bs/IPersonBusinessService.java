/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package org.lr.ibatis.service.bs;

import java.util.List;

import org.lr.ibatis.bean.Person;

public interface IPersonBusinessService
{
    List<Person> findAllPerson();

    Person findPersonById(String id);

    void createOrUpdatePerson(Person person);

    void createAndUpdatePersonList(List<Person> persons);
}
