package com.codedifferently.lesson15;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class EmployeeTest {

  @Test
  void testConstructorAndGetters() {
    Employee employee = new Employee(1, "John Doe");

    assertEquals(1, employee.getId());
    assertEquals("John Doe", employee.getName());
    assertNull(employee.getDepartment());
    assertEquals(0.0, employee.getSalary());
  }

  @Test
  void testSettersAndGetters() {
    Employee employee = new Employee(2, "Jane Doe");

    employee.setId(3);
    employee.setName("John Smith");
    employee.setDepartment("Engineering");
    employee.setSalary(75000.50);

    assertEquals(3, employee.getId());
    assertEquals("John Smith", employee.getName());
    assertEquals("Engineering", employee.getDepartment());
    assertEquals(75000.50, employee.getSalary());
  }

  @Test
  void testChangeDepartmentAndSalary() {
    Employee employee = new Employee(4, "Test User");

    employee.setDepartment("HR");
    employee.setSalary(55000);

    assertEquals("HR", employee.getDepartment());
    assertEquals(55000, employee.getSalary());
  }
}
