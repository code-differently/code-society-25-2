package com.codedifferently.lesson15;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeManagerTest {
  Employee employee;
  EmployeeManager manager;

  @BeforeEach
  public void setUp() {
    employee = new Employee(1, "John Doe", "Software Engineer", 75000.0);
    manager = new EmployeeManager();
  }

  @Test
  public void testEmployeeManagerBasicOperations() {
    // Test creation and initial state
    assertNotNull(manager);
    assertEquals(0, manager.getEmployeeCount());

    // Test adding employee
    manager.addEmployee(employee);
    assertEquals(1, manager.getEmployeeCount());

    // Test getting employee
    Employee retrieved = manager.getEmployee(1);
    assertEquals(employee.getId(), retrieved.getId());
    assertEquals(employee.getName(), retrieved.getName());
    assertEquals(employee.getDepartment(), retrieved.getDepartment());
    assertEquals(employee.getSalary(), retrieved.getSalary());

    // Test updating employee
    Employee updatedEmployee = new Employee(1, "John Smith", "Senior Engineer", 85000.0);
    manager.updateEmployee(updatedEmployee);
    Employee retrievedUpdated = manager.getEmployee(1);
    assertEquals("John Smith", retrievedUpdated.getName());
    assertEquals("Senior Engineer", retrievedUpdated.getDepartment());
    assertEquals(85000.0, retrievedUpdated.getSalary());

    // Test removing employee
    manager.removeEmployee(1);
    assertEquals(0, manager.getEmployeeCount());
  }

  @Test
  public void testEmployeeManagerExceptions() {
    String expectedMessage = "Employee does not in collection with id 999";

    // Test getEmployee not found
    try {
      manager.getEmployee(999);
      assertEquals(true, false, "Expected IllegalArgumentException for getEmployee");
    } catch (IllegalArgumentException e) {
      assertEquals(expectedMessage, e.getMessage());
    }

    // Test updateEmployee not found
    Employee testEmployee = new Employee(999, "John Doe", "Software Engineer", 75000.0);
    try {
      manager.updateEmployee(testEmployee);
      assertEquals(true, false, "Expected IllegalArgumentException for updateEmployee");
    } catch (IllegalArgumentException e) {
      assertEquals(expectedMessage, e.getMessage());
    }

    // Test removeEmployee not found
    try {
      manager.removeEmployee(999);
      assertEquals(true, false, "Expected IllegalArgumentException for removeEmployee");
    } catch (IllegalArgumentException e) {
      assertEquals(expectedMessage, e.getMessage());
    }
  }

  @Test
  public void testMultipleEmployees() {
    Employee emp2 = new Employee(2, "Jane Smith", "Marketing", 65000.0);
    Employee emp3 = new Employee(3, "Bob Johnson", "Sales", 55000.0);

    manager.addEmployee(employee);
    manager.addEmployee(emp2);
    manager.addEmployee(emp3);

    assertEquals(3, manager.getEmployeeCount());

    Employee retrieved1 = manager.getEmployee(1);
    Employee retrieved2 = manager.getEmployee(2);
    Employee retrieved3 = manager.getEmployee(3);

    assertEquals("John Doe", retrieved1.getName());
    assertEquals("Jane Smith", retrieved2.getName());
    assertEquals("Bob Johnson", retrieved3.getName());
  }
}
