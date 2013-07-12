package zhwb.study.javabase.collection;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Calculate {
	public static void main(final String[] args) {
		BigInteger big = new BigInteger("2");
		BigInteger result = big.pow(100);
		System.out.println(result);
		
		List<Person> plis = new ArrayList<Person>();
        Person p = new Person();
        p.age = 18;
        plis.add(p);

        Map<String, Person> map = new HashMap<String, Person>();
        for (Person person : plis)
        {
            map.put("1", person);
        }

        for (Entry<String, Person> person : map.entrySet())
        {
            person.getValue().age = 20;
            person.getValue().name = "Jack";
        }

        for (Person person : plis)
        {
            System.out.println(person.age);
            System.out.println(person.name);
        }
    }

    static class Person
    {
        public Person()
        {
        }

        String name;

        int age;
    }
	}
