package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeManagerTest {
  EmployeeManager employeeManager;

  @BeforeEach
  void setup() {
    employeeManager = new EmployeeManager();
    employeeManager.addEmployee(new Employee(1, "Jane Doe", "HR", 50000.00));
  }

  @Test
  void testEmployeeManager() {
    assertThat(employeeManager).isNotNull();
  }

  @Test
  void testAddEmployee() {
    employeeManager.addEmployee(new Employee(2, "Mary Sue", "Data Analyst", 90000.00));
    assertThat(employeeManager.getEmployeeCount()).isEqualTo(2);
  }

  @Test
  void testGetEmployee() {
    assertThat(employeeManager.getEmployee(1)).isNotNull();
  }

  @Test
  void testUpdateEmployee() {
    Employee updatedEmployee = new Employee(1, "Jane Doe", "Finance", 60000.00);
    employeeManager.updateEmployee(updatedEmployee);
    Employee retrievedEmployee = employeeManager.getEmployee(1);
    String actual = retrievedEmployee.getDepartment();
    assertThat(actual).isEqualTo("Finance");
  }

  @Test
  void testGetEmployeeFails() {
    assertThatThrownBy(
            () -> {
              employeeManager.getEmployee(3);
            })
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Employee does not in collection with id 3");
  }

  @Test
  void testRemoveEmployee() {
    employeeManager.removeEmployee(1);
    assertThat(employeeManager.getEmployeeCount()).isEqualTo(0);

    assertThatThrownBy(
            () -> {
              employeeManager.getEmployee(1);
            })
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Employee does not in collection with id 1");
  }
}
