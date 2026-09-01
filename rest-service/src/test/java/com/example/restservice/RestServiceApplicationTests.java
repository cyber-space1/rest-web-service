package com.example.restservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class RestServiceApplicationTests {
// Break down the process of adding employees and querying employees into individual unit tests


	@Test
	void creatingNewEmployee() {
		Employee employee1 = new Employee(1, "First","Employee","test@gmail.com","tester");

		assert(employee1.getFirst_Name().equals("First"));
		assert(employee1.getLast_Name().equals("Employee"));
		assert(employee1.getEmail().equals("test@gmail.com"));
		assert(employee1.getTitle().equals("tester"));
		assert(employee1.getEmployee_id() == 1);
	}

	@Test
	void addingNewEmployee() {
		Employee employee1 = new Employee(1, "First","Employee","test@gmail.com","tester");
		Employees employees = new Employees();

		employees.setEmployeeList(List.of(employee1));

		assert(employees.getEmployeeList().getFirst().getFirst_Name().equals("First"));
		assert(employees.getEmployeeList().getFirst().getLast_Name().equals("Employee"));
		assert(employees.getEmployeeList().getFirst().getEmail().equals("test@gmail.com"));
		assert(employees.getEmployeeList().getFirst().getTitle().equals("tester"));
		assert(employees.getEmployeeList().getFirst().getEmployee_id() == 1);
	}

	@Test
	void getFullEmployeesList() {
		Employees employees = new Employees();

		Employee employee1 = new Employee(1, "First","Employee","test@gmail.com","tester");
		Employee employee2 = new Employee(2, "Second","Employee","test2@gmail.com","tester");

		employees.getEmployeeList()
				.add(employee1);
		employees.getEmployeeList()
				.add(employee2);

		assert(employees.getEmployeeList().containsAll(List.of(employee1, employee2)));
	}
}
