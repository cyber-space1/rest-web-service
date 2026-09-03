package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // Acts as bridge between database layer and business layer
public class EmployeeManager {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeManager(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // private static Employees employees = new Employees();

    // Sample employees
//    static {
//        employees.getEmployeeList()
//                .add(new Employee(1, "John", "Warwick", "jw@gmail.com", "CEO"));
//        employees.getEmployeeList()
//                .add(new Employee(2, "Hikaru", "Nakamura", "hn@gmail.com", "CFO"));
//        employees.getEmployeeList()
//                .add(new Employee(3, "Masayoshi", "Takanaka", "mt@gmail.com", "CMO"));
//    }

    public Employees getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();

        Employees employees = new Employees();
        employees.setEmployeeList(employeeList);

        return employees;
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> updateEmployee(Integer id, Employee updatedData) {
        return employeeRepository.findById(id).map(existingEmployee -> {
            existingEmployee.setFirst_Name(updatedData.getFirst_Name());
            existingEmployee.setLast_Name(updatedData.getLast_Name());
            existingEmployee.setEmail(updatedData.getEmail());
            existingEmployee.setTitle(updatedData.getTitle());
            return employeeRepository.save(existingEmployee);
        });
    }

    public boolean deleteEmployee(Integer id) {
        if (!employeeRepository.existsById(id)) {
            return false;
        }
        employeeRepository.deleteById(id);
        return true;
    }
}
