package com.codedifferently.lesson15;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeManagerTest {

  private EmployeeManager manager;
  private Employee employee;

  @BeforeEach
  void setUp() {
    manager = new EmployeeManager();
    employee = new Employee(1, "John Doe");
  }

  @Test
  void testAddEmployeeAndCount() {
    manager.addEmployee(employee);
    assertEquals(1, manager.getEmployeeCount());
  }

  @Test
  void testGetEmployee() {
    manager.addEmployee(employee);
    Employee retrieved = manager.getEmployee(1);
    assertNotNull(retrieved);
    assertEquals(employee.getId(), retrieved.getId());
  }

  @Test
  void testUpdateEmployee() {
    manager.addEmployee(employee);
    Employee updated = new Employee(1, "Jane Doe"); // same ID, new name
    manager.updateEmployee(updated);
    Employee retrieved = manager.getEmployee(1);
    assertEquals("Jane Doe", retrieved.getName());
  }

  @Test
  void testRemoveEmployee() {
    manager.addEmployee(employee);
    manager.removeEmployee(1);
    assertEquals(0, manager.getEmployeeCount());
  }

  @Test
  void testGetEmployeeThrowsExceptionWhenNotFound() {
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              manager.getEmployee(999);
            });
    assertTrue(exception.getMessage().contains("Employee does not in collection"));
  }

  @Test
  void testUpdateEmployeeThrowsExceptionWhenNotFound() {
    Employee fake = new Employee(2, "Ghost");
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              manager.updateEmployee(fake);
            });
    assertTrue(exception.getMessage().contains("Employee does not in collection"));
  }

  @Test
  void testRemoveEmployeeThrowsExceptionWhenNotFound() {
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              manager.removeEmployee(3);
            });
    assertTrue(exception.getMessage().contains("Employee does not in collection"));
  }
}
