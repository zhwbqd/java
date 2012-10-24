/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.study;

public class DAOImpl<T> implements IDAO<T>
{

    @Override
    public boolean insert(T obj)
    {
        System.out.println("insert into database");
        return false;
    }

    @Override
    public boolean update(T obj)
    {
        System.out.println("update into database");
        return false;
    }

    @Override
    public T find(T obj)
    {
        System.out.println("find from database");
        return null;
    }

    @Override
    public boolean delete(T obj)
    {
        System.out.println("delete from database");
        return false;
    }

}
