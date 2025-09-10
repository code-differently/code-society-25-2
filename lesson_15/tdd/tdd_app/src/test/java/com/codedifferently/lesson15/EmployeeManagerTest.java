package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeManagerTest {
  private EmployeeManager manager;
  private Employee emp1;
  private Employee emp2;

  @BeforeEach
  void setUp() {
    manager = new EmployeeManager();
    emp1 = new Employee(1, "Alice", "Engineering", 95000.0);
    emp2 = new Employee(2, "Bob", "HR", 50000.0);
  }

  @Test
  void addEmployee_increasesCount() {
    manager.addEmployee(emp1);
    assertThat(manager.getEmployeeCount()).isEqualTo(1);
    manager.addEmployee(emp2);
    assertThat(manager.getEmployeeCount()).isEqualTo(2);
  }

  @Test
  void getEmployee_returnsCorrectEmployee() {
    manager.addEmployee(emp1);
    Employee found = manager.getEmployee(1);
    assertThat(found.getName()).isEqualTo("Alice");
  }

  @Test
  void getEmployee_throwsIfNotFound() {
    assertThatThrownBy(() -> manager.getEmployee(99))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Employee does not exist in collection with id 99");
  }

  @Test
  void updateEmployee_changesEmployee() {
    manager.addEmployee(emp1);
    Employee updated = new Employee(1, "Alicia", "Engineering", 96000.0);
    manager.updateEmployee(updated);
    Employee found = manager.getEmployee(1);
    assertThat(found.getName()).isEqualTo("Alicia");
    assertThat(found.getSalary()).isEqualTo(96000.0);
  }

  @Test
  void updateEmployee_throwsIfNotFound() {
    assertThatThrownBy(() -> manager.updateEmployee(emp2))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Employee does not exist in collection with id 2");
  }

  @Test
  void removeEmployee_deletesEmployee() {
    manager.addEmployee(emp1);
    manager.removeEmployee(1);
    assertThat(manager.getEmployeeCount()).isEqualTo(0);
  }

  @Test
  void removeEmployee_throwsIfNotFound() {
    assertThatThrownBy(() -> manager.removeEmployee(2))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Employee does not exist in collection with id 2");
  }
}
