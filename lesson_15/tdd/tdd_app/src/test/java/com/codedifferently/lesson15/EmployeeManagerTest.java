package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeManagerTest {

  private EmployeeManager manager;
  private Employee employee1;
  private Employee employee2;

  @BeforeEach
  void setUp() {
    manager = new EmployeeManager();
    employee1 = new Employee(1, "John Doe", "Engineering", 75000.0);
    employee2 = new Employee(2, "Jane Smith", "Marketing", 65000.0);
  }

  @Test
  void testAddEmployee() {
    manager.addEmployee(employee1);
    assertThat(manager.getEmployeeCount()).isEqualTo(1);
  }

  @Test
  void testGetEmployee() {
    manager.addEmployee(employee1);
    Employee retrieved = manager.getEmployee(1);
    assertThat(retrieved).isEqualTo(employee1);
    assertThat(retrieved.getName()).isEqualTo("John Doe");
  }

  @Test
  void testGetEmployeeThrowsExceptionWhenNotFound() {
    assertThatThrownBy(() -> manager.getEmployee(999))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Employee does not in collection with id 999");
  }

  @Test
  void testUpdateEmployee() {
    manager.addEmployee(employee1);
    employee1.setName("John Updated");
    manager.updateEmployee(employee1);
    Employee updated = manager.getEmployee(1);
    assertThat(updated.getName()).isEqualTo("John Updated");
  }

  @Test
  void testRemoveEmployee() {
    manager.addEmployee(employee1);
    manager.addEmployee(employee2);
    assertThat(manager.getEmployeeCount()).isEqualTo(2);

    manager.removeEmployee(1);
    assertThat(manager.getEmployeeCount()).isEqualTo(1);
  }

  @Test
  void testGetEmployeeCount() {
    assertThat(manager.getEmployeeCount()).isEqualTo(0);
    manager.addEmployee(employee1);
    assertThat(manager.getEmployeeCount()).isEqualTo(1);
    manager.addEmployee(employee2);
    assertThat(manager.getEmployeeCount()).isEqualTo(2);
  }
}
