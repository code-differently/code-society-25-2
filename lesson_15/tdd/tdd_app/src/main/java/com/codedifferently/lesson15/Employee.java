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

  public String getDetails() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.getClass().getSimpleName()).append("{");
    try {
      java.lang.reflect.Field[] fields = this.getClass().getDeclaredFields();
      java.util.Arrays.sort(
          fields, java.util.Comparator.comparing(java.lang.reflect.Field::getName));
      for (int i = 0; i < fields.length; i++) {
        java.lang.reflect.Field f = fields[i];
        f.setAccessible(true);
        Object v = f.get(this);
        sb.append(f.getName()).append("=").append(String.valueOf(v));
        if (i < fields.length - 1) sb.append(", ");
      }
    } catch (IllegalAccessException ignored) {
    }
    sb.append("}");
    return sb.toString();
  }
}
