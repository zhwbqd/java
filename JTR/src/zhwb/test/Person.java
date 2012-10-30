package zhwb.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person {
	private Integer personId;
	private String name;

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		List<Person> list1 = new ArrayList<Person>();
		List<Person> list2 = new ArrayList<Person>();
		for (int i = 0; i < 1000; i++) {
			Person p1 = new Person();
			Person p2 = new Person();
			p1.setPersonId(i);
			p2.setPersonId(i * 2);
			list1.add(p1);
			list2.add(p2);
		}
		List<Person> samePerson = getSamePerson(list1, list2);
		System.out.println("Use hashmap to get same one");
		for (Person person : samePerson) {
			System.out.print(person.getPersonId());
		}
		List<Person> samePerson1 = getSamePerson1(list1, list2);
		System.out.println("Use foreach to get same one");
		for (Person person : samePerson1) {
			System.out.print(person.getPersonId());
		}
	}

	private static List<Person> getSamePerson1(List<Person> list1,
			List<Person> list2) {

		long st = System.nanoTime();

		List<Person> sameList = new ArrayList<Person>();
		List<Person> maxList = list1;
		List<Person> minList = list2;
		if (list2.size() > list1.size()) {
			maxList = list2;
			minList = list1;
		}
		for (Person p1 : minList) {
			for (Person p2 : maxList) {
				if (p1.getPersonId().equals(p2.getPersonId())) {
					sameList.add(p1);
				}
			}
		}

		System.out.println("\ngetSamePerson1 total times "
				+ (System.nanoTime() - st));
		return sameList;
	}

	private static List<Person> getSamePerson(List<Person> list1,
			List<Person> list2) {
		long st = System.nanoTime();

		List<Person> sameList = new ArrayList<Person>();
		List<Person> maxList = list1;
		List<Person> minList = list2;
		if (list2.size() > list1.size()) {
			maxList = list2;
			minList = list1;
		}
		Map<Integer, Person> sharedMap = new HashMap<Integer, Person>(
				maxList.size());
		Map<Integer, Person> minMap = new HashMap<Integer, Person>(
				minList.size());
		for (Person person : minList) {
			minMap.put(person.getPersonId(), person);
		}
		for (Person person : maxList) {
			Person cc = minMap.get(person.getPersonId());
			if (cc != null) {
				sharedMap.put(person.getPersonId(), cc);
				continue;
			}
		}
		for (Map.Entry<Integer, Person> entry : sharedMap.entrySet()) {
			sameList.add(entry.getValue());
		}

		System.out.println("\ngetSamePerson total times "
				+ (System.nanoTime() - st));
		return sameList;
	}
}
