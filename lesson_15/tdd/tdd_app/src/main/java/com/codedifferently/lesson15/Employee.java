package com.codedifferently.lesson15;

public class Employee {
  private int id;
  private String name;
  private String department;
  private double salary;
  private String details; 

  public Employee(int id, String name, String department, double salary, String details) {
    this.id = id;
    this.name = name;
    this.department = department;
    this.salary = salary;
    this.details = details;
  }

  // Getters and setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }
  public String getDetails() {
    return details;
  }

  public void setDetails(String details)  {
    this.details = details; 
  }
}

 