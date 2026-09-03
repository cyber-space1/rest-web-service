package com.example.restservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

@SpringBootTest
class RestServiceApplicationTests {
// Break down the process of adding employees and querying employees into individual unit tests

	@Autowired
	private EmployeeController employeeController;

	@Autowired
	private EmployeeManager employeeManager;

	@Autowired
	private EmployeeRepository employeeRepository;

	@BeforeEach
	void cleanDB() {
		employeeRepository.deleteAll();
	}

	// General Tests

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
	void employeeToString() {
		Employee employee1 = new Employee(1, "First","Employee","test@gmail.com","tester");
		assert(employee1.toString().equals("Employee [id=1, firstName=First, lastName=Employee, email=test@gmail.com]"));
	}

	// GET and POST Tests

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
		Employee employee1 = new Employee(null, "First","Employee","test@gmail.com","tester");
		Employee employee2 = new Employee(null, "Second","Employee","test2@gmail.com","tester");

		employeeManager.addEmployee(employee1);
		employeeManager.addEmployee(employee2);

		Employees employeeList = employeeManager.getAllEmployees();
		assert(employeeList.getEmployeeList().size() == 2);
	}

	@Test
	void employeeControllerAddAndGet() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Employee employee1 = new Employee(null, "First","Employee","test@gmail.com","tester");

		var response = employeeController.addEmployee(employee1);
		assert(response.getStatusCode().value() == 201);

		Employees employeeList = employeeController.getEmployees();
		assert(employeeList.getEmployeeList().size() == 1);
		assert(employeeList.getEmployeeList().getFirst().getFirst_Name().equals("First"));
	}

	// PUT Tests

	@Test
	void employeeManagerUpdateExistingEmployee() {
		Employee employee1 = new Employee(null, "First", "Employee", "test@gmail.com", "tester");
		Employee saved = employeeManager.addEmployee(employee1);

		Employee updatedDetails = new Employee(null, "FirstNew", "EmployeeNew", "testnew@gmail.com", "testerAdv");
		Optional<Employee> result = employeeManager.updateEmployee(saved.getEmployee_id(), updatedDetails);

		assert(result.isPresent());
		assert(result.get().getFirst_Name().equals("FirstNew"));
		assert(result.get().getLast_Name().equals("EmployeeNew"));
		assert(result.get().getEmail().equals("testnew@gmail.com"));
		assert(result.get().getTitle().equals("testerAdv"));
		assert(result.get().getEmployee_id().equals(saved.getEmployee_id()));
	}

	@Test
	void employeeManagerUpdateNonExistentEmployeeReturnsEmpty() {
		Employee updatedDetails = new Employee(null, "FirstNew", "EmployeeNew", "testnew@gmail.com", "testerAdv");
		Optional<Employee> result = employeeManager.updateEmployee(99999, updatedDetails);

		assert(result.isEmpty());
	}

	@Test
	void employeeControllerUpdateReturns200WhenFound() {
		Employee employee1 = new Employee(null, "First", "Employee", "test@gmail.com", "tester");
		Employee saved = employeeManager.addEmployee(employee1);

		Employee updatedDetails = new Employee(null, "FirstNew", "EmployeeNew", "testnew@gmail.com", "testerAdv");
		ResponseEntity<Employee> response = employeeController.updateEmployee(saved.getEmployee_id(), updatedDetails);

		assert(response.getStatusCode().value() == 200);
		assert(response.getBody() != null);
		assert(response.getBody().getFirst_Name().equals("FirstNew"));
		assert(response.getBody().getLast_Name().equals("EmployeeNew"));
		assert(response.getBody().getEmail().equals("testnew@gmail.com"));
		assert(response.getBody().getTitle().equals("testerAdv"));
		assert(response.getBody().getEmployee_id().equals(saved.getEmployee_id()));
	}

	@Test
	void employeeControllerUpdateReturns404WhenNotFound() {
		Employee updatedDetails = new Employee(null, "FirstNew", "EmployeeNew", "testnew@gmail.com", "testerAdv");
		ResponseEntity<Employee> response = employeeController.updateEmployee(99999, updatedDetails);

		assert(response.getStatusCode().value() == 404);
	}

	// DELETE Tests

	@Test
	void employeeManagerDeleteExistingEmployee() {
		Employee employee1 = new Employee(null, "First", "Employee", "test@gmail.com", "tester");
		Employee saved = employeeManager.addEmployee(employee1);

		boolean deleted = employeeManager.deleteEmployee(saved.getEmployee_id());
		assert(deleted);
		assert(!employeeRepository.existsById(saved.getEmployee_id()));
	}

	@Test
	void employeeManagerDeleteNonExistentEmployeeReturnsFalse() {
		boolean deleted = employeeManager.deleteEmployee(99999);
		assert(!deleted);
	}

	@Test
	void employeeControllerDeleteReturns204WhenSuccessful() {
		Employee employee1 = new Employee(null, "First", "Employee", "test@gmail.com", "tester");
		Employee saved = employeeManager.addEmployee(employee1);

		ResponseEntity<Void> response = employeeController.deleteEmployee(saved.getEmployee_id());
		assert(response.getStatusCode().value() == 204);
		assert(!employeeRepository.existsById(saved.getEmployee_id()));
	}

	@Test
	void employeeControllerDeleteReturns404WhenNotFound() {
		ResponseEntity<Void> response = employeeController.deleteEmployee(99999);
		assert(response.getStatusCode().value() == 404);
	}
}
