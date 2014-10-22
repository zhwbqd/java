package domain;

import java.util.ArrayList;
import java.util.List;

public class Employer {
    private int age;
    private int service;
    private String name;
    private List<Employee> employees = new ArrayList<Employee>();

    public Employer() {

    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
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
