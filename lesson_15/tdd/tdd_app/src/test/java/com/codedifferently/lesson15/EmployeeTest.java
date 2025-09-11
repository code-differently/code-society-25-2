package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Employee class.
 *
 * <p>These tests verify the core functionality of the Employee class, including: - Setting and
 * getting employee properties (id, name, department, salary) - Retrieving a string description of
 * the employee
 *
 * <p>Usage: Run with your preferred JUnit 5 runner or via `./gradlew test`
 */
class EmployeeTest {

  /** Test: Sets all employee properties and verifies getters. */
  @Test
  public void testSetEmployee() {
    // Arrange
    Employee employee = new Employee(1, "John Doe", "Engineering", 750000.00);

    // Act
    employee.setId(2);
    employee.setName("Jane Smith");
    employee.setDepartment("Marketing");
    employee.setSalary(650000.00);

    // Assert
    assertThat(employee.getId()).isEqualTo(2);
    assertThat(employee.getName()).isEqualTo("Jane Smith");
    assertThat(employee.getDepartment()).isEqualTo("Marketing");
    assertThat(employee.getSalary()).isEqualTo(650000.00);
  }

  /** Test: Verifies the getDescription (getDetails) method returns the correct string. */
  @Test
  public void testGetEmployeeDetails() {
    // Arrange
    Employee employee = new Employee(1, "John Doe", "Engineering", 750000.00);

    // Act & Assert
    assertThat(employee.getDetails())
        .isEqualTo("ID: 1, Name: John Doe, Department: Engineering, Salary: 750000.0");
  }
}
