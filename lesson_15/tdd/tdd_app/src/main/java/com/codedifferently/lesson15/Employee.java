package com.codedifferently.lesson15;

public class Employee {
  private int id;
  private String name;
  private String department;
  private double salary;

  public Employee(int id, String name, String department, double salary) {
    this.id = id;
    this.name = name;
    this.department = department;
    this.salary = salary;
  }

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

  // Required by Lesson 15
  public String getDetails() {
    String cleanName = (name == null) ? "" : name.trim().replaceAll("\\s+", " ");
    StringBuilder sb = new StringBuilder();
    sb.append("Employee[id=").append(id);
    sb.append(", name=").append(cleanName);
    if (department != null && !department.trim().isEmpty()) {
      sb.append(", dept=").append(department.trim());
    }
    sb.append(", salary=").append(salary);
    sb.append("]");
    return sb.toString();
  }
}
