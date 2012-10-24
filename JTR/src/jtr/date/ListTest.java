/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package jtr.date;

import java.util.ArrayList;
import java.util.List;

public class ListTest
{

    /**
     * Description goes here.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        List<Person> list = new ArrayList<Person>();
        Person p = new Person();
        list.add(p);
        p.setName("jack");
        p.setTitle("programmer");
        System.out.println(list.get(0).getName());
        System.out.println(list.get(0).getTitle());

    }

}
