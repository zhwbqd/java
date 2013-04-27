/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.javabase.collection;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class TreeSetModifier
{
    public static void main(final String[] args)
    {
        Map<String, Person> map = new TreeMap<String, Person>();
        map.put("tom", new Person(16));
        map.put("jtie", new Person(25));
        map.put("zzla", new Person(17));
        for (Entry<String, Person> set : map.entrySet())
        {
            if (set.getKey().equals("tom"))
            {
                map.remove("tom");
                System.out.println("find tom");
            }
            else
            {
                System.out.println(set.getValue().getAge());
            }
        }
        System.out.println(map.size());
    }
}

class Person
{
    private int age;

    /**
     * Get the property age
     *
     * @return the age
     */
    public final int getAge()
    {
        return age;
    }

    /**
     * Set the property age
     *
     * @param age the age to set
     */
    public final void setAge(final int age)
    {
        this.age = age;
    }

    /**
     * @param age
     */
    public Person(final int age)
    {
        super();
        this.age = age;
    }

}
