package com.example.restservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // Spring Data JPA automatically adds CRUD methods
}
