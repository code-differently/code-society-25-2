/**
 * Unit tests for the EmployeeManager class.
 *
 * <p>These tests verify the core functionality of the EmployeeManager, including: - Adding,
 * updating, and removing employees - Retrieving employees and their properties - Handling
 * non-existent employees - Counting employees in the collection
 *
 * <p>Test coverage includes: - Happy path for all CRUD operations - Exception handling for invalid
 * employee lookups - Validation of employee department and salary retrieval
 *
 * <p>Usage: Run with your preferred JUnit 5 runner or via `./gradlew test`
 */
package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class EmployeeManagerTest {

  /** Test: Adds an employee and verifies retrieval by ID and name. */
  @Test
  public void testAddEmployee() {
    // Arrange
    EmployeeManager employeeManager = new EmployeeManager();
    Employee employee = new Employee(1, "John Doe", "Engineering", 750000.00);

    // Act
    employeeManager.addEmployee(employee);
    Employee retrievedEmployee = employeeManager.getEmployee(1);

    // Assert
    assertThat(retrievedEmployee).isNotNull();
    assertThat(retrievedEmployee.getName()).isEqualTo("John Doe");
  }

  /** Test: Updates an employee's department and verifies the update. */
  @Test
  public void testUpdateEmployee() {
    // Arrange
    EmployeeManager employeeManager = new EmployeeManager();
    Employee employee = new Employee(1, "John Doe", "Engineering", 750000.00);
    employeeManager.addEmployee(employee);

    // Act
    employee.setDepartment("Marketing");
    employeeManager.updateEmployee(employee);
    Employee updatedEmployee = employeeManager.getEmployee(1);

    // Assert
    assertThat(updatedEmployee.getDepartment()).isEqualTo("Marketing");
  }

  /** Test: Removes an employee and verifies the employee count is zero. */
  @Test
  public void testRemoveEmployee() {
    // Arrange
    EmployeeManager employeeManager = new EmployeeManager();
    Employee employee = new Employee(1, "John Doe", "Engineering", 750000.00);
    employeeManager.addEmployee(employee);

    // Act
    employeeManager.removeEmployee(1);

    // Assert
    assertThat(employeeManager.getEmployeeCount()).isEqualTo(0);
  }

  /** Test: Adds two employees and verifies the employee count. */
  @Test
  public void testGetEmployeeCount() {
    // Arrange
    EmployeeManager employeeManager = new EmployeeManager();
    Employee employee1 = new Employee(1, "John Doe", "Engineering", 750000.00);
    Employee employee2 = new Employee(2, "Jane Smith", "Marketing", 650000.00);
    employeeManager.addEmployee(employee1);
    employeeManager.addEmployee(employee2);

    // Act
    int count = employeeManager.getEmployeeCount();

    // Assert
    assertThat(count).isEqualTo(2);
  }

  /** Test: Attempts to retrieve a non-existent employee and expects an exception. */
  @Test
  public void testGetNonExistentEmployee() {
    // Arrange
    EmployeeManager employeeManager = new EmployeeManager();

    // Act & Assert
    try {
      employeeManager.getEmployee(999);
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).isEqualTo("Employee does not in collection with id 999");
    }
  }

  /** Test: Adds an employee and verifies the department property. */
  @Test
  public void testGetEmployeeDepartment() {
    // Arrange
    EmployeeManager employeeManager = new EmployeeManager();
    Employee employee = new Employee(1, "John Doe", "Engineering", 750000.00);
    employeeManager.addEmployee(employee);

    // Act
    Employee retrievedEmployee = employeeManager.getEmployee(1);

    // Assert
    assertThat(retrievedEmployee.getDepartment()).isEqualTo("Engineering");
  }

  /** Test: Adds an employee and verifies the salary property. */
  @Test
  public void testGetEmployeeSalary() {
    // Arrange
    EmployeeManager employeeManager = new EmployeeManager();
    Employee employee = new Employee(1, "John Doe", "Engineering", 750000.00);
    employeeManager.addEmployee(employee);

    // Act
    Employee retrievedEmployee = employeeManager.getEmployee(1);

    // Assert
    assertThat(retrievedEmployee.getSalary()).isEqualTo(750000.00);
  }
}
