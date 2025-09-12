package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class EmployeeManagerTest {

  @Test
  public void testAddEmployee() {
    // Arrange
    EmployeeManager employeeManager = new EmployeeManager();
    Employee employee = new Employee(1, "John", "Doe", 50000.0);

    // Act
    employeeManager.addEmployee(employee);

    // Assert
    Employee retrievedEmployee = employeeManager.getEmployee(1);
    assertThat(retrievedEmployee).isEqualTo(employee);
    assertThat(employeeManager.getEmployeeCount()).isEqualTo(1);
  }

  @Test
  public void testGetEmployee() {

    // Arrange
    EmployeeManager employeeManager = new EmployeeManager();
    Employee employee = new Employee(1, "John", "Doe", 50000.0);
    employeeManager.addEmployee(employee);

    // Act
    Employee retrievedEmployee = employeeManager.getEmployee(1);

    // Assert
    assertThat(retrievedEmployee).isEqualTo(employee);
  }

  @Test
  public void testUpdateEmployee() {
    // Arrange
    EmployeeManager employeeManager = new EmployeeManager();
    Employee employee = new Employee(1, "John", "Doe", 50000.0);
    employeeManager.addEmployee(employee);
    Employee updatedEmployee = new Employee(1, "John", "Smith", 55000.0);

    // Act
    employeeManager.updateEmployee(updatedEmployee);

    // Assert
    Employee retrievedEmployee = employeeManager.getEmployee(1);
    assertThat(retrievedEmployee).isEqualTo(updatedEmployee);
  }

  @Test
  public void testAssertEmployee() {
    // Arrange
    EmployeeManager employeeManager = new EmployeeManager();
    Employee employee = new Employee(1, "John", "Doe", 50000.0);
    employeeManager.addEmployee(employee);

    // Act
    Employee retrievedEmployee = employeeManager.getEmployee(1);

    // Assert
    assertThat(retrievedEmployee).isEqualTo(employee);
  }
}
