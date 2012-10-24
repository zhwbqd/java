/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package study.spring;

import com.spring.annotation.SelfDefinedException;

public class DAOImpl<Object> implements IDAO<Object>
{

    public boolean insert(Object obj)
    {
        System.out.println("insert into database");
        return false;
    }

    public boolean update(Object obj)
    {
        System.out.println("update into database");
        return false;
    }

    public Object find(Object obj)
        throws SelfDefinedException
    {
        System.out.println("find from database");
        if (obj == null)
        {
            throw new SelfDefinedException("if failed");
        }
        return obj;
    }

    public boolean delete(Object obj)
    {
        System.out.println("delete from database");
        return false;
    }

}
