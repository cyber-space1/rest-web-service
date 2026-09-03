package com.example.restservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class RestServiceApplicationTests {
// Break down the process of adding employees and querying employees into individual unit tests

	@Autowired
	private EmployeeController employeeController;

	@Autowired
	private EmployeeManager employeeManager;

	@Test
	void employeeGettersAndSetter() {
		Employee employee1 = new Employee(1, "First","Employee","test@gmail.com","tester");

		employee1.setEmployee_id(2);
		employee1.setFirst_Name("FirstNew");
		employee1.setLast_Name("EmployeeNew");
		employee1.setEmail("testnew@gmail.com");
		employee1.setTitle("testerAdv");

		assert(employee1.getFirst_Name().equals("FirstNew"));
		assert(employee1.getLast_Name().equals("EmployeeNew"));
		assert(employee1.getEmail().equals("testnew@gmail.com"));
		assert(employee1.getTitle().equals("testerAdv"));
		assert(employee1.getEmployee_id() == 2);
	}

	@Test
	void addingNewEmployee() {
		Employee employee1 = new Employee(1, "First","Employee","test@gmail.com","tester");
		Employees employees = new Employees();

		employees.getEmployeeList().add(employee1);

		assert(employees.getEmployeeList().contains(employee1));
	}

	@Test
	void employeesGetFullList() {
		Employees employees = new Employees();

		Employee employee1 = new Employee(1, "First","Employee","test@gmail.com","tester");
		Employee employee2 = new Employee(2, "Second","Employee","test2@gmail.com","tester");

		employees.setEmployeeList(List.of(employee1, employee2));

		assert(employees.getEmployeeList().containsAll(List.of(employee1, employee2)));
	}

	@Test
	void employeeManagerGetsAllAndAdds() {
		EmployeeManager employeeManager = new EmployeeManager();
		int initialLength = employeeManager.getAllEmployees().getEmployeeList().size();
		Employee employee1 = new Employee(1, "First","Employee","test@gmail.com","tester");

		employeeManager.addEmployee(employee1);

		List<Employee> employeeList = employeeManager.getAllEmployees().getEmployeeList();
		assert(employeeList.contains(employee1));
		assert(employeeList.size() == initialLength + 1);
	}

	@Test
	void employeeControllerGetFullList() {
		// needs mock HTTP request
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Employee employee1 = new Employee(1, "First","Employee","test@gmail.com","tester");
		employeeController.addEmployee(employee1);

		assert(employeeController.getEmployees().getEmployeeList().contains(employee1));
	}

	@Test
	void employeeToString() {
		Employee employee1 = new Employee(1, "First","Employee","test@gmail.com","tester");
		assert(employee1.toString().equals("Employee [id=1, firstName=First, lastName=Employee, email=test@gmail.com]"));
	}
}
