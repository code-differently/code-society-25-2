package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Lesson15Test {
  Employee employee;

  @BeforeEach
  public void setUp() {
    employee = new Employee(1, "John Doe", "Software Engineer", 75000.0);
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

  @Test
  public void testEmployeeCreationAndGetters() {
    // Test employee creation and all getter methods
    assertNotNull(employee);
    assertEquals(1, employee.getId());
    assertEquals("John Doe", employee.getName());
    assertEquals("Software Engineer", employee.getDepartment());
    assertEquals(75000.0, employee.getSalary());
  }

  @Test
  public void testEmployeeSettersAndDetails() {
    // Test getDetails with initial values
    String expectedInitial = "ID: 1Name: John DoeDepartment: Software EngineerSalary: $75000.0";
    assertEquals(expectedInitial, employee.getDetails());

    // Test all setter methods
    employee.setId(2);
    employee.setName("Jane Smith");
    employee.setDepartment("Marketing");
    employee.setSalary(80000.0);

    // Verify all changes through getters
    assertEquals(2, employee.getId());
    assertEquals("Jane Smith", employee.getName());
    assertEquals("Marketing", employee.getDepartment());
    assertEquals(80000.0, employee.getSalary());

    // Test getDetails with updated values
    String expectedUpdated = "ID: 2Name: Jane SmithDepartment: MarketingSalary: $80000.0";
    assertEquals(expectedUpdated, employee.getDetails());
  }

  @Test
  public void testEmployeeManagerBasicOperations() {
    EmployeeManager manager = new EmployeeManager();

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
    EmployeeManager manager = new EmployeeManager();
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
    EmployeeManager manager = new EmployeeManager();
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
