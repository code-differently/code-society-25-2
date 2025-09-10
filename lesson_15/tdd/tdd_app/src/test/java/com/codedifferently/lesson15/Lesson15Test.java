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
  public void testEmployeeCreation() {

    assertNotNull(employee);
  }

  @Test
  public void testGetName() {
    assertEquals("John Doe", employee.getName());
  }

  @Test
  public void testGetDepartment() {
    assertEquals("Software Engineer", employee.getDepartment());
  }

  @Test
  public void testGetSalary() {
    assertEquals(75000.0, employee.getSalary());
  }

  @Test
  public void testGetId() {
    assertEquals(1, employee.getId());
  }

  @Test
  public void testSetId() {
    employee.setId(2);
    assertEquals(2, employee.getId());
  }

  @Test
  public void testSetName() {
    employee.setName("Jane Smith");
    assertEquals("Jane Smith", employee.getName());
  }

  @Test
  public void testSetDepartment() {
    employee.setDepartment("Marketing");
    assertEquals("Marketing", employee.getDepartment());
  }

  @Test
  public void testSetSalary() {
    employee.setSalary(80000.0);
    assertEquals(80000.0, employee.getSalary());
  }

  @Test
  public void testGetDetails() {
    String expected = "ID: 1Name: John DoeDepartment: Software EngineerSalary: $75000.0";
    assertEquals(expected, employee.getDetails());
  }

  @Test
  public void testEmployeeManagerCreation() {
    EmployeeManager manager = new EmployeeManager();
    assertNotNull(manager);
    assertEquals(0, manager.getEmployeeCount());
  }

  @Test
  public void testAddEmployee() {
    EmployeeManager manager = new EmployeeManager();
    manager.addEmployee(employee);
    assertEquals(1, manager.getEmployeeCount());
  }

  @Test
  public void testGetEmployee() {
    EmployeeManager manager = new EmployeeManager();
    manager.addEmployee(employee);
    Employee retrieved = manager.getEmployee(1);

    assertEquals(employee.getId(), retrieved.getId());
    assertEquals(employee.getName(), retrieved.getName());
    assertEquals(employee.getDepartment(), retrieved.getDepartment());
    assertEquals(employee.getSalary(), retrieved.getSalary());
  }

  @Test
  public void testUpdateEmployee() {
    EmployeeManager manager = new EmployeeManager();
    manager.addEmployee(employee);

    Employee updatedEmployee = new Employee(1, "John Smith", "Senior Engineer", 85000.0);
    manager.updateEmployee(updatedEmployee);

    Employee retrieved = manager.getEmployee(1);
    assertEquals("John Smith", retrieved.getName());
    assertEquals("Senior Engineer", retrieved.getDepartment());
    assertEquals(85000.0, retrieved.getSalary());
  }

  @Test
  public void testRemoveEmployee() {
    EmployeeManager manager = new EmployeeManager();
    manager.addEmployee(employee);

    assertEquals(1, manager.getEmployeeCount());
    manager.removeEmployee(1);
    assertEquals(0, manager.getEmployeeCount());
  }

  @Test
  public void testGetEmployeeNotFound() {
    EmployeeManager manager = new EmployeeManager();

    try {
      manager.getEmployee(999);
      // Should not reach here
      assertEquals(true, false, "Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Employee does not in collection with id 999", e.getMessage());
    }
  }

  @Test
  public void testUpdateEmployeeNotFound() {
    EmployeeManager manager = new EmployeeManager();
    Employee testEmployee = new Employee(999, "John Doe", "Software Engineer", 75000.0);

    try {
      manager.updateEmployee(testEmployee);
      // Should not reach here
      assertEquals(true, false, "Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Employee does not in collection with id 999", e.getMessage());
    }
  }

  @Test
  public void testRemoveEmployeeNotFound() {
    EmployeeManager manager = new EmployeeManager();

    try {
      manager.removeEmployee(999);
      // Should not reach here
      assertEquals(true, false, "Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertEquals("Employee does not in collection with id 999", e.getMessage());
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
