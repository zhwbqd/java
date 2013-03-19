/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package serialization.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SerializationTest
{

    /**
     * Description goes here.
     *
     * @param args
     */
    public static void main(final String[] args)
    {
        Map<Object, Object> map = new HashMap<Object, Object>();

        Person p1 = new Person("jack", 1);
        Person p2 = new Person("alex", 2);
        map.put(p1, "help");
        map.put(p2, "help");

        System.out.println(map);
        for (Entry<Object, Object> entry : map.entrySet())
        {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

    }

}

class Person
{
    private String name;

    private Integer Id;

    /**
     * Get the property name
     *
     * @return the name
     */
    public final String getName()
    {
        return name;
    }

    /**
     * @param name
     * @param id
     */
    public Person(final String name, final Integer id)
    {
        super();
        this.name = name;
        Id = id;
    }

    /**
     * Set the property name
     *
     * @param name the name to set
     */
    public final void setName(final String name)
    {
        this.name = name;
    }

    /**
     * Get the property id
     *
     * @return the id
     */
    public final Integer getId()
    {
        return Id;
    }

    /**
     * Set the property id
     *
     * @param id the id to set
     */
    public final void setId(final Integer id)
    {
        Id = id;
    }

    @Override
    public int hashCode()
    {
        return 3;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj instanceof Person)
        {
            if (this.Id.equals(((Person)obj).getId()) && this.name.equals(((Person)obj).getName()))
            {
                return true;
            }
        }
        return false;
    }
}