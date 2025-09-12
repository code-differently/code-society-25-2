package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class EmployeeManagerTest {
  @Test
  public void testAddEmployee() {
    // Arrange
    EmployeeManager manager = new EmployeeManager();
    Employee employee = new Employee(1, "Rosie", "Music Production", 90000.0);

    // Act
    manager.addEmployee(employee);
    Employee retrieved = manager.getEmployee(1);

    // Assert
    assertThat(retrieved).isEqualTo(employee);
  }

  @Test
  public void testRemoveEmployee() {
    // Arrange
    EmployeeManager manager = new EmployeeManager();
    Employee employee = new Employee(1, "Rosie", "Music Production", 90000.0);
    manager.addEmployee(employee);

    // Act
    manager.removeEmployee(1);

    // Assert
    assertThatThrownBy(() -> manager.getEmployee(1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Employee does not in collection with id 1");
  }

  @Test
  public void testGetEmployee() {
    // Arrange
    EmployeeManager manager = new EmployeeManager();
    Employee employee = new Employee(1, "Rosie", "Music Production", 90000.0);
    manager.addEmployee(employee);

    // Act
    Employee retrieved = manager.getEmployee(1);

    // Assert
    assertThat(retrieved).isEqualTo(employee);
  }

  @Test
  public void testUpdateEmployee() {
    // Arrange
    EmployeeManager manager = new EmployeeManager();
    Employee employee = new Employee(1, "Rosie", "Music Production", 90000.0);
    manager.addEmployee(employee);

    // Act
    employee.setName("Rosie Updated");
    manager.updateEmployee(employee);
    Employee retrieved = manager.getEmployee(1);

    // Assert
    assertThat(retrieved).isEqualTo(employee);
  }

  @Test
  public void testGetEmployeeCount() {
    // Arrange
    EmployeeManager manager = new EmployeeManager();
    Employee employee1 = new Employee(1, "Rosie", "Music Production", 90000.0);
    Employee employee2 = new Employee(2, "John", "Engineering", 80000.0);
    manager.addEmployee(employee1);
    manager.addEmployee(employee2);
    // Act
    int count = manager.getEmployeeCount();
    // Assert
    assertThat(count).isEqualTo(2);
  }
}
