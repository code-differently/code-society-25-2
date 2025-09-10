package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class EmployeeTest {
  @Test
  public void testGetID() {
    // Arrange
    Employee employee = new Employee(1, "Rosie", "Music Production", 90000.0);

    // Act
    int id = employee.getId();

    // Assert
    assertThat(id).isEqualTo(1);
  }

  @Test
  public void testSetID() {
    // Arrange
    Employee employee = new Employee(1, "Rosie", "Music Production", 90000.0);

    // Act
    employee.setId(2);
    int id = employee.getId();

    // Assert
    assertThat(id).isEqualTo(2);
  }

  @Test
  public void testGetName() {
    // Arrange
    Employee employee = new Employee(1, "Rosie", "Music Production", 90000.0);

    // Act
    String name = employee.getName();

    // Assert
    assertThat(name).isEqualTo("Rosie");
  }

  @Test
  public void testSetName() {
    // Arrange
    Employee employee = new Employee(2, "Liam", "Artist", 25000.0);

    // Act
    employee.setName("Liam");
    String name = employee.getName();

    // Assert
    assertThat(name).isEqualTo("Liam");
  }

  @Test
  public void testGetDepartment() {
    // Arrange
    Employee employee = new Employee(1, "Rosie", "Music Production", 90000.0);

    // Act
    String department = employee.getDepartment();

    // Assert
    assertThat(department).isEqualTo("Music Production");
  }

  @Test
  public void testSetDepartment() {
    // Arrange
    Employee employee = new Employee(65, "Robert", "Sound Engineering", 85000.0);

    // Act
    employee.setDepartment("Sound Engineering");
    String department = employee.getDepartment();

    // Assert
    assertThat(department).isEqualTo("Sound Engineering");
  }

  @Test
  public void testGetSalary() {
    // Arrange
    Employee employee = new Employee(1, "Rosie", "Music Production", 90000.0);

    // Act
    double salary = employee.getSalary();
    // Assert
    assertThat(salary).isEqualTo(90000.0);
  }

  @Test
  public void testSetSalary() {
    // Arrange
    Employee employee = new Employee(45, "Alice", "Marketing", 65000.0);
    // Act
    employee.setSalary(65000.0);
    double salary = employee.getSalary();
    // Assert
    assertThat(salary).isEqualTo(65000.0);
  }

  @Test
  public void testEmployeeGetDetails() {
    // Arrange
    Employee employee = new Employee(1, "Rosie", "Music Production", 90000.0);

    // Act
    String details = employee.getDetails();

    // Assert
    assertThat(details)
        .isEqualTo("ID: 1, Name: Rosie, Department: Music Production, Salary: 90000.0");
  }

  @Test
  public void testEmployeeGetDetailsEqualityForIdenticalEmployees() {
    // Arrange
    Employee employee1 = new Employee(1, "Sarah", "Music Production", 90000.0);
    Employee employee2 = new Employee(1, "Sarah", "Music Production", 90000.0);

    // Act
    String details1 = employee1.getDetails();
    String details2 = employee2.getDetails();

    // Assert
    assertThat(details1).isEqualTo(details2);
  }

  @Test
  public void testEmployeeGetDetailsWithNullName() {
    // Arrange
    Employee employee = new Employee(1, null, "Music Production", 90000.0);

    // Act
    String details = employee.getDetails();

    // Assert
    assertThat(details)
        .isEqualTo("ID: 1, Name: null, Department: Music Production, Salary: 90000.0");
  }
}
