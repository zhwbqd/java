package domain;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private int age;
    private int service;
    private int days;
    private String name;
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getService() {
        return service;
    }
    public void setService(int service) {
        this.service = service;
    }
    public int getDays() {
        return days;
    }
    public void setDays(int days) {
        this.days = days;
    }

    public static void main(String[] args) {
        List<Employee> list = new ArrayList<Employee>();
        Employee a1 = new Employee();
        a1.setAge(1);
        Employee a2 = a1;
        list.add(a1);
        System.out.print(list.contains(a2));
    }
}

