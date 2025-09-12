package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class EmployeeTest {
  @Test
  public void testGetID() {
    // Arrange
    Employee employee = new Employee(1, "Linda", "Vibe Coordinator", 95000.0);

    // Act
    int id = employee.getId();

    // Assert
    assertThat(id).isEqualTo(1);
  }

  @Test
  public void testSetID() {
    // Arrange
    Employee employee = new Employee(1, "Linda", "Vibe Coordinator", 95000.0);

    // Act
    employee.setId(2);
    int id = employee.getId();

    // Assert
    assertThat(id).isEqualTo(2);
  }

  @Test
  public void testGetName() {
    // Arrange
    Employee employee = new Employee(1001, "Kai", "Chore Manager", 40000.0);

    // Act
    String name = employee.getName();

    // Assert
    assertThat(name).isEqualTo("Kai");
  }

  @Test
  public void testSetName() {
    // Arrange
    Employee employee = new Employee(1001, "Kai", "Chore Manager", 40000.0);

    // Act
    employee.setName("Abel");
    String name = employee.getName();

    // Assert
    assertThat(name).isEqualTo("Abel");
  }

  @Test
  public void testGetDepartment() {
    // Arrange
    Employee employee = new Employee(1001, "Kai", "Chore Manager", 40000.0);

    // Act
    String department = employee.getDepartment();

    // Assert
    assertThat(department).isEqualTo("Chore Manager");
  }

  @Test
  public void testSetDepartment() {
    // Arrange
    Employee employee = new Employee(1001, "Kai", "Chore Manager", 40000.0);

    // Act
    employee.setDepartment("Cleaning Crew");
    String department = employee.getDepartment();

    // Assert
    assertThat(department).isEqualTo("Cleaning Crew");
  }

  @Test
  public void testGetSalary() {
    // Arrange
    Employee employee = new Employee(114, "Abel", "Intern", 25000.0);

    // Act
    double salary = employee.getSalary();

    // Assert
    assertThat(salary).isEqualTo(25000.0);
  }

  @Test
  public void testSetSalary() {
    // Arrange
    Employee employee = new Employee(114, "Abel", "Intern", 25000.0);

    // Act
    employee.setSalary(30000.0);
    double salary = employee.getSalary();

    // Assert
    assertThat(salary).isEqualTo(30000.0);
  }

  @Test
  public void testGetDetails() {
    // Arrange
    Employee employee = new Employee(114, "Abel", "Intern", 30000.0);

    // Act
    String details = employee.getDetails();

    // Assert
    assertThat(details).isEqualTo("ID: 114, Name: Abel, Department: Intern, Salary: 30000.0");
  }
}
