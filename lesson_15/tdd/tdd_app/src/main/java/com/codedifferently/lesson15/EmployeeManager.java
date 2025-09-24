package com.codedifferently.lesson15;

import java.util.HashMap;
import java.util.Map;

public class EmployeeManager {
  private final Map<Integer, Employee> employees;

  public EmployeeManager() {
    employees = new HashMap<>();
  }

  public void addEmployee(Employee employee) {
    if (employee == null) {
      throw new IllegalArgumentException("Employee cannot be null");
    }
    employees.put(employee.getId(), employee);
  }

  public Employee getEmployee(int id) {
    assertEmployeeExists(id);
    return employees.get(id);
  }

  public void updateEmployee(Employee employee) {
    if (employee == null) {
      throw new IllegalArgumentException("Employee cannot be null");
    }
    assertEmployeeExists(employee.getId());
    employees.put(employee.getId(), employee);
  }

  public void removeEmployee(int id) {
    assertEmployeeExists(id);
    employees.remove(id);
  }

  public int getEmployeeCount() {
    return employees.size();
  }

  private void assertEmployeeExists(int id) {
    if (!employees.containsKey(id)) {
      throw new IllegalArgumentException("Employee does not exist in collection with id " + id);
    }
  }
}
