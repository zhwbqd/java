package zhwb.study.javabase.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

class Person implements Comparable<Person>
{
    private String name;

    private int age;

    public Person(final String name, final int age)
    {
        this.name = name;
        this.age = age;
    }

    public Person(final String name)
    {
        this.name = name;
    }

    /** {@inheritDoc}
     *  @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof Person))
        {
            return false;
        }
        Person other = (Person)obj;
        if (name == null)
        {
            if (other.name != null)
            {
                return false;
            }
        }
        else if (!name.equals(other.name))
        {
            return false;
        }
        return true;
    }

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
     * Get the property name
     *
     * @return the name
     */
    public final String getName()
    {
        return name;
    }

    /** {@inheritDoc}
     *  @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
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
     * Set the property name
     *
     * @param name the name to set
     */
    public final void setName(final String name)
    {
        this.name = name;
    }

    @Override
    public int compareTo(final Person o)
    {
        int ageDiff = age - o.age;
        if (ageDiff != 0)
        {
            return ageDiff;
        }

        int nameDiff = name.compareTo(o.name);
        if (nameDiff != 0)
        {
            return nameDiff;
        }
        return 0;
    }

    /** {@inheritDoc}
     *  @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("Person [name=%s, age=%s]", name, age);
    }
}

/*按名字查找，按年龄段查找，时间复杂度都*/
public class PersonFinder
{
    public static void main(final String[] args)
    {
        PersonFinder p = new PersonFinder();
        NavigableSet<Person> resultSet = p.prepopulate();
        System.out.println(resultSet);

        System.out.println(p.getAgeFromName("Jack")); //bug

        System.out.println(p.getPersonsBelowAge(30));
        System.out.println(p.getPersonsByAboveAge(30));
        System.out.println(p.getPersonsByRangeAge(20, 35));
    }

    private NavigableSet<Person> set = new TreeSet<Person>();

    public int getAgeFromName(final String name)
    {
        Person search = new Person(name);
        Person result = set.ceiling(search);
        if (result != null)
        {
            return result.getAge();
        }
        return -1;
    }

    public List<Person> getPersonsByRangeAge(final int min, final int max)
    {
        if (min >= 0 && max >= 0)
        {
            return new ArrayList<Person>(set.subSet(new Person("", min), new Person("", max)));
        }
        throw new IllegalArgumentException("Input not valid");
    }

    public List<Person> getPersonsBelowAge(final int age)
    {
        if (age >= 0)
        {
            return new ArrayList<Person>(set.headSet(new Person("", age)));
        }
        throw new IllegalArgumentException("Input not valid");
    }

    public List<Person> getPersonsByAboveAge(final int age)
    {
        if (age >= 0)
        {
            return new ArrayList<Person>(set.tailSet(new Person("", age)));
        }
        throw new IllegalArgumentException("Input not valid");
    }

    private NavigableSet<Person> prepopulate()
    {
        Person p1 = new Person("Jack", 23);
        Person p2 = new Person("Alex", 30);
        Person p3 = new Person("Mick", 55);
        Person p4 = new Person("Welsey", 12);
        Person p5 = new Person("Lee", 66);
        Person p6 = new Person("Tom", 23);
        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);
        set.add(p5);
        set.add(p6);
        return set;
    }

}
