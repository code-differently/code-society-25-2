package com.codedifferently.lesson15;

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
    assertNotNull(retrieved);
    assertEquals("Alice", retrieved.getName());
    assertEquals("Engineering", retrieved.getDepartment());
    assertEquals(75000, retrieved.getSalary());
  }

  @Test
  void testRemoveEmployee() {
    EmployeeManager manager = new EmployeeManager();
    Employee emp = new Employee(2, "Bob", "HR", 60000);

    manager.addEmployee(emp);
    assertEquals(1, manager.getEmployeeCount());

    manager.removeEmployee(2);
    assertEquals(0, manager.getEmployeeCount());
    assertThrows(IllegalArgumentException.class, () -> manager.getEmployee(2));
  }
}
