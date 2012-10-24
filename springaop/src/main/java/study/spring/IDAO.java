/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package study.spring;

import com.spring.annotation.SelfDefinedException;

public interface IDAO<T>
{
    boolean insert(T obj);

    boolean update(T obj);

    T find(T obj)
        throws SelfDefinedException;

    boolean delete(T obj);

}
