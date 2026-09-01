package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeManager employeeManager;

    @GetMapping("/employees")
    public Employees getEmployees(){
        return employeeManager.getAllEmployees();
    }

    @PostMapping("/employees")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee){
        Integer id = employeeManager.getAllEmployees().getEmployeeList().size() + 1;
        employee.setEmployee_id(id);

        employeeManager.addEmployee(employee);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employee.getEmployee_id())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
