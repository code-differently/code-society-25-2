package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Lesson15Test {

  private Employee employee;
  private EmployeeManager employeeManager;

  @BeforeEach
  void setUp() {
    employee = new Employee(1, "John Doe", "Engineering", 75000.0);
    employeeManager = new EmployeeManager();
  }

  @Test
  public void testLesson15() {
    assertThat(new Lesson15()).isNotNull();
  }

  @Test
  public void testGetGreeting() {
    // Act
    Lesson15.main(null);
  }

  // ===== Employee Tests =====

  @Test
  public void testEmployeeConstructor() {
    // Assert
    assertThat(employee.getId()).isEqualTo(1);
    assertThat(employee.getName()).isEqualTo("John Doe");
    assertThat(employee.getDepartment()).isEqualTo("Engineering");
    assertThat(employee.getSalary()).isEqualTo(75000.0);
  }

  @Test
  public void testEmployeeSetters() {
    // Act
    employee.setId(2);
    employee.setName("Jane Smith");
    employee.setDepartment("Marketing");
    employee.setSalary(80000.0);

    // Assert
    assertThat(employee.getId()).isEqualTo(2);
    assertThat(employee.getName()).isEqualTo("Jane Smith");
    assertThat(employee.getDepartment()).isEqualTo("Marketing");
    assertThat(employee.getSalary()).isEqualTo(80000.0);
  }

  @Test
  public void testEmployeeGetDetails_shouldReturnFormattedString() {
    // This test will fail initially since getDetails() doesn't exist yet
    // Expected format: "Employee [ID: 1, Name: John Doe, Department: Engineering, Salary:
    // $75000.00]"

    // Act
    String details = employee.getDetails();

    // Assert
    assertThat(details)
        .isEqualTo("Employee [ID: 1, Name: John Doe, Department: Engineering, Salary: $75000.00]");
  }

  @Test
  public void testEmployeeGetDetails_withDifferentEmployee() {
    // Arrange
    Employee emp2 = new Employee(99, "Alice Johnson", "HR", 65000.50);

    // Act
    String details = emp2.getDetails();

    // Assert
    assertThat(details)
        .isEqualTo("Employee [ID: 99, Name: Alice Johnson, Department: HR, Salary: $65000.50]");
  }

  // ===== EmployeeManager Tests =====

  @Test
  public void testEmployeeManagerConstructor() {
    // Assert
    assertThat(employeeManager.getEmployeeCount()).isEqualTo(0);
  }

  @Test
  public void testAddEmployee() {
    // Act
    employeeManager.addEmployee(employee);

    // Assert
    assertThat(employeeManager.getEmployeeCount()).isEqualTo(1);
    assertThat(employeeManager.getEmployee(1)).isEqualTo(employee);
  }

  @Test
  public void testAddMultipleEmployees() {
    // Arrange
    Employee emp2 = new Employee(2, "Jane Smith", "Marketing", 70000.0);

    // Act
    employeeManager.addEmployee(employee);
    employeeManager.addEmployee(emp2);

    // Assert
    assertThat(employeeManager.getEmployeeCount()).isEqualTo(2);
    assertThat(employeeManager.getEmployee(1)).isEqualTo(employee);
    assertThat(employeeManager.getEmployee(2)).isEqualTo(emp2);
  }

  @Test
  public void testGetEmployee_whenEmployeeExists() {
    // Arrange
    employeeManager.addEmployee(employee);

    // Act
    Employee retrieved = employeeManager.getEmployee(1);

    // Assert
    assertThat(retrieved).isEqualTo(employee);
    assertThat(retrieved.getName()).isEqualTo("John Doe");
  }

  @Test
  public void testGetEmployee_whenEmployeeDoesNotExist_shouldThrowException() {
    // Act & Assert
    assertThatThrownBy(() -> employeeManager.getEmployee(999))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Employee does not in collection with id 999");
  }

  @Test
  public void testUpdateEmployee() {
    // Arrange
    employeeManager.addEmployee(employee);
    Employee updatedEmployee = new Employee(1, "John Updated", "Engineering", 85000.0);

    // Act
    employeeManager.updateEmployee(updatedEmployee);

    // Assert
    Employee retrieved = employeeManager.getEmployee(1);
    assertThat(retrieved.getName()).isEqualTo("John Updated");
    assertThat(retrieved.getSalary()).isEqualTo(85000.0);
    assertThat(employeeManager.getEmployeeCount()).isEqualTo(1);
  }

  @Test
  public void testUpdateEmployee_whenEmployeeDoesNotExist_shouldThrowException() {
    // Arrange
    Employee nonExistentEmployee = new Employee(999, "Non Existent", "None", 0.0);

    // Act & Assert
    assertThatThrownBy(() -> employeeManager.updateEmployee(nonExistentEmployee))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Employee does not in collection with id 999");
  }

  @Test
  public void testRemoveEmployee() {
    // Arrange
    employeeManager.addEmployee(employee);
    assertThat(employeeManager.getEmployeeCount()).isEqualTo(1);

    // Act
    employeeManager.removeEmployee(1);

    // Assert
    assertThat(employeeManager.getEmployeeCount()).isEqualTo(0);
    assertThatThrownBy(() -> employeeManager.getEmployee(1))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void testRemoveEmployee_whenEmployeeDoesNotExist_shouldThrowException() {
    // Act & Assert
    assertThatThrownBy(() -> employeeManager.removeEmployee(999))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Employee does not in collection with id 999");
  }

  @Test
  public void testEmployeeManagerWithMultipleOperations() {
    // Arrange
    Employee emp1 = new Employee(1, "Alice", "HR", 60000.0);
    Employee emp2 = new Employee(2, "Bob", "IT", 70000.0);
    Employee emp3 = new Employee(3, "Charlie", "Finance", 65000.0);

    // Act - Add employees
    employeeManager.addEmployee(emp1);
    employeeManager.addEmployee(emp2);
    employeeManager.addEmployee(emp3);

    // Assert initial state
    assertThat(employeeManager.getEmployeeCount()).isEqualTo(3);

    // Act - Update employee
    Employee updatedEmp2 = new Employee(2, "Bob Updated", "IT", 75000.0);
    employeeManager.updateEmployee(updatedEmp2);

    // Assert after update
    assertThat(employeeManager.getEmployee(2).getName()).isEqualTo("Bob Updated");
    assertThat(employeeManager.getEmployee(2).getSalary()).isEqualTo(75000.0);

    // Act - Remove employee
    employeeManager.removeEmployee(3);

    // Assert after removal
    assertThat(employeeManager.getEmployeeCount()).isEqualTo(2);
    assertThatThrownBy(() -> employeeManager.getEmployee(3))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
