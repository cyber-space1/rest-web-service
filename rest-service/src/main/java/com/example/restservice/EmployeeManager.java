package com.example.restservice;

import org.springframework.stereotype.Repository;

@Repository //acts as bridge between database layer and business layer
public class EmployeeManager {
    private static Employees employees = new Employees();

    // Sample employees
    static {
        employees.getEmployeeList()
                .add(new Employee(1, "John", "Warwick", "jw@gmail.com", "CEO"));
        employees.getEmployeeList()
                .add(new Employee(2, "Hikaru", "Nakamura", "hn@gmail.com", "CFO"));
        employees.getEmployeeList()
                .add(new Employee(3, "Masayoshi", "Takanaka", "mt@gmail.com", "CMO"));
    }

    public Employees getAllEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.getEmployeeList().add(employee);
    }
}
