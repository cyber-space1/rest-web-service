# Employee Management REST Web Service

A production-ready RESTful web service built with **Java** and **Spring Boot** using **Test-Driven Development (TDD)**. Includes input validation, relational database persistence with **Spring Data JPA**, and automated build/test pipelines via **Gradle**.

---

## Key Features

- **RESTful Endpoints:** Full CRUD operations for managing employee records through HTTP requests
- **Strict Payload Validation:** Uses Jakarta Validation (`@Valid`, `@NotBlank`) to reject malformed/null payloads
- **Fail-Fast Deserialization:** Jackson configured to reject unrecognized / extra JSON fields with `400 Bad Request`
- **Relational Persistence:** Seamless ORM layer integrating **Spring Data JPA / Hibernate** with **PostgreSQL** (and in-memory **H2** for isolated integration testing)
- **Comprehensive Test Suite:** Developed following TDD methodology with **JUnit 5** and **AssertJ**, reaching **83%+ instruction coverage** tracked via **JaCoCo**

---

## Architecture & Project Structure

```text
rest-service/
├── src/
│   ├── main/
│   │   ├── java/com/example/restservice/
│   │   │   ├── Employee.java                # JPA Entity with validation annotations
│   │   │   ├── EmployeeRepository.java      # Spring Data JPA repository interface
│   │   │   ├── EmployeeController.java      # REST controller exposing CRUD routes
│   │   │   ├── EmployeeNotFoundException.java
│   │   │   ├── GlobalExceptionHandler.java  # Centralized validation & error responses
│   │   │   └── RestServiceApplication.java  # Spring Boot entry point
│   │   └── resources/
│   │       └── application.properties       # DB connection & Jackson configuration
│   └── test/
│       └── java/com/example/restservice/
│           ├── EmployeeControllerTest.java  # MockMvc web slice & integration tests
│           └── EmployeeRepositoryTest.java  # DataJpaTest persistence verification
├── build.gradle                             # Build scripts and dependency management
└── gradlew                                  # Gradle wrapper
```

---

## API Specification

### Base URL
`http://localhost:8080`

### Endpoints

| HTTP Method | Endpoint | Description | Expected Status |
| :--- | :--- | :--- | :--- |
| `GET` | `/employees` | Retrieve all employees | `200 OK` |
| `GET` | `/employees/{id}` | Retrieve a specific employee by ID | `200 OK` / `404 Not Found` |
| `POST` | `/employees` | Create a new employee record | `201 Created` / `400 Bad Request` |
| `PUT` | `/employees/{id}` | Update an existing employee record | `200 OK` / `400 Bad Request` / `404 Not Found` |
| `DELETE` | `/employees/{id}` | Delete an employee by ID | `204 No Content` / `404 Not Found` |

---

## Sample Payloads

### 1. Create Employee (`POST /employees`)

**Request Body:**
```json
{
  "first_Name": "Ada",
  "last_Name": "Lovelace",
  "email": "ada.lovelace@example.com",
  "title": "Software Engineer"
}
```

**Response (`201 Created`):**
```json
{
  "employee_id": 1,
  "first_Name": "Ada",
  "last_Name": "Lovelace",
  "email": "ada.lovelace@example.com",
  "title": "Software Engineer"
}
```

### 2. Validation Error Handling (`400 Bad Request`)

If required fields are missing or unexpected properties are included in the payload:
```json
{
  "status": 400,
  "error": "Missing or null required fields",
  "details": {
    "email": "email must not be null or empty",
    "first_Name": "first_Name must not be null or empty"
  }
}
```

---

## Getting Started

### Prerequisites
- **JDK 17** or higher installed
- **PostgreSQL** running locally (or configured container)

### 1. Database Configuration
Update `src/main/resources/application.properties` with your PostgreSQL credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/employeedb
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Strict JSON deserialization (reject unknown properties)
spring.jackson.deserialization.fail-on-unknown-properties=true
```

### 2. Build & Run
From the `rest-service` directory, run:

```bash
# Build the project
./gradlew build

# Run the Spring Boot application
./gradlew bootRun
```

### 3. Running Tests & Generating Code Coverage Report

```bash
# Execute unit and integration test suites
./gradlew test

# Generate JaCoCo coverage report (HTML report saved to build/reports/jacoco/test/html/index.html)
./gradlew jacocoTestReport
```
