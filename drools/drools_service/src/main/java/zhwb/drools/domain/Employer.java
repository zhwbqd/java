package zhwb.drools.domain;

import java.util.ArrayList;

public class Employer {
    private int age;
    private int service;
    private String name;
    private ArrayList<Employee> employees = new ArrayList<Employee>();

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
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

    public Employer(int _age, int _service) {
        this.age = _age;
        this.service = _service;
    }

    public boolean addEmployee(Employee e) {
        boolean sign = false;
        for (Employee ee : employees) {
            if (ee.getName().equals(e.getName())) {
                sign = true;
            }
        }
        if (!sign)
            employees.add(e);
        return !sign;
    }

    public void removeEmployee(Employee e) {
        employees.remove(e);
    }

    public int count() {
        return employees.size();
    }
}
