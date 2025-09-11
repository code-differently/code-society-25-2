package com.codedifferently.lesson15;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeTest {
  Employee employee;

  @BeforeEach
  public void setUp() {
    employee = new Employee(1, "John Doe", "Software Engineer", 75000.0);
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
}
