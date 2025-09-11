package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeManagerTest {

  EmployeeManager employeeManager;

  @BeforeEach
  void setUp() {
    employeeManager = new EmployeeManager();
  }

  @Test
  void testAddEmployee() {
    Employee emp = new Employee(1, "John Doe", "Engineering", 50000.0);
    employeeManager.addEmployee(emp);
    assertThat(employeeManager.getEmployee(1)).isEqualTo(emp);
  }

  @Test
  void testRemoveEmployee() {
    Employee emp = new Employee(1, "John Doe", "Engineering", 50000.0);
    employeeManager.addEmployee(emp);
    employeeManager.removeEmployee(1);
    // After removal, getEmployee should throw or return null
    try {
      employeeManager.getEmployee(1);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // expected exception
    }
  }

  @Test
  void testUpdateEmployee() {
    Employee emp = new Employee(1, "John Doe", "Engineering", 50000.0);
    employeeManager.addEmployee(emp);
    Employee updated = new Employee(1, "Jane Doe", "HR", 60000.0);
    employeeManager.updateEmployee(updated);
    assertThat(employeeManager.getEmployee(1)).isEqualTo(updated);
  }

  @Test
  void testGetEmployeeCount() {
    Employee emp1 = new Employee(1, "John Doe", "Engineering", 50000.0);
    Employee emp2 = new Employee(2, "Jane Doe", "HR", 60000.0);
    employeeManager.addEmployee(emp1);
    employeeManager.addEmployee(emp2);
    assertThat(employeeManager.getEmployeeCount()).isEqualTo(2);
  }
}
