/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.study.pattern;

import java.util.HashMap;
import java.util.Map;



public class PatternTest
{

    /**
     * Description goes here.
     *
     * @param args
     */
    public static void main(final String[] args)
    {
        //        String input = "Can not have $ at all {items}";
        //        String input1 = "I have a ${user} pattern";
        //        String regex = ".*\\$\\{\\w+\\}.*";
        //
        //        System.out.println(Pattern.matches(regex, input));
        //        System.out.println(Pattern.matches(regex, input1));
        //        System.out.println(Pattern.matches(regex, " ${user} "));

//        System.out.println("en-US-ee".replace("-", "_"));
        
        //        Locale []locales = Locale.getAvailableLocales();
        //        for (Locale l : locales) {
        //            System.out.println(l);
        //        }

        Map<String, Person> m1 = new HashMap<String,Person>();
        Person p = new Person();
        p.setName("Jack");
        
        m1.put("Person", p);

        Map<String, Person> m2 = new HashMap<String,Person>();
        
        m2.put("Annimal", p);
        
        Person p1 = m1.get("Person");
        p1.setName("Alex");
        
        System.out.println(m2.get("Annimal").getName());
    }

}

class Person
{
    private String name;

    /**
     * Get the property name
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set the property name
     *
     * @param name the name to set
     */
    public void setName(final String name)
    {
        this.name = name;
    }
}