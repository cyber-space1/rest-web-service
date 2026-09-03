package com.example.restservice;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="employees")
public class Employee {
    // Annotations defining the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employee_id;

    // Annotations validating manually input fields
    @NotBlank(message = "first_Name must not be null or empty")
    private String first_Name;

    @NotBlank(message = "last_Name must not be null or empty")
    private String last_Name;

    @NotBlank(message = "email must not be null or empty")
    private String email;

    @NotBlank(message = "title must not be null or empty")
    private String title;

    // Explicit default constructor required for JPA reflection
    public Employee() {}

    public Employee (Integer employee_id, String first_Name, String last_Name, String email, String title){
        this.employee_id = employee_id;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.email = email;
        this.title = title;
    }

    public Integer getEmployee_id() {return employee_id;}
    public void setEmployee_id(Integer id) {this.employee_id = id;}

    public String getFirst_Name() {return first_Name;}
    public void setFirst_Name(String first_Name) {this.first_Name = first_Name;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getLast_Name() {return last_Name;}
    public void setLast_Name(String last_Name) {this.last_Name = last_Name;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    @Override
    public String toString(){
        return "Employee [id=" + employee_id + ", firstName=" + first_Name +
                ", lastName=" + last_Name + ", email=" + email + "]";
    }
}
