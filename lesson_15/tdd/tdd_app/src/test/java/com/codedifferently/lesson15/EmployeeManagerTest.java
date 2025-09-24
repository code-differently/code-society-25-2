package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeManagerTest {

  private EmployeeManager employeeManager;
  private Employee testEmployee;

  @BeforeEach
  void setUp() {
    employeeManager = new EmployeeManager();
    testEmployee = new Employee(1, "Alice", "Engineering", 75000);
  }

  @Test
  void testAddAndGetEmployee() {
    employeeManager.addEmployee(testEmployee);

    Employee retrieved = employeeManager.getEmployee(1);
    assertThat(retrieved).isNotNull();
    assertThat(retrieved.getName()).isEqualTo("Alice");
    assertThat(retrieved.getDepartment()).isEqualTo("Engineering");
    assertThat(retrieved.getSalary()).isEqualTo(75000);
  }

  @Test
  void testRemoveEmployee() {
    EmployeeManager manager = new EmployeeManager();
    Employee emp = new Employee(2, "Bob", "HR", 60000);

    manager.addEmployee(emp);
    System.out.println("Employee count after add: " + manager.getEmployeeCount());
    System.out.println("Can get employee 2: " + (manager.getEmployee(2) != null));

    manager.removeEmployee(2);
    assertEquals(0, manager.getEmployeeCount());
    assertThatThrownBy(() -> manager.getEmployee(2)).isInstanceOf(IllegalArgumentException.class);
  }
}
