package com.example.restservice;

public class Employee {
    private Integer employee_id;
    private String first_Name;
    private String last_Name;
    private String email;
    private String title;

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
