package com.codedifferently.lesson15;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeeTest {

  @Test
  void testConstructorAndGetters() {
    Employee emp = new Employee(1, "Jayden", "Engineering", 75000.0);

    assertEquals(1, emp.getId());
    assertEquals("Jayden", emp.getName());
    assertEquals("Engineering", emp.getDepartment());
    assertEquals(75000.0, emp.getSalary());
  }

  @Test
  void testSettersUpdateFields() {
    Employee emp = new Employee(2, "Ellis", "Sales", 50000.0);

    emp.setId(3);
    emp.setName("J.E.");
    emp.setDepartment("Marketing");
    emp.setSalary(65000.0);

    assertEquals(3, emp.getId());
    assertEquals("J.E.", emp.getName());
    assertEquals("Marketing", emp.getDepartment());
    assertEquals(65000.0, emp.getSalary());
  }

  @Test
  void testDifferentEmployeesNotEqualByDefault() {
    Employee emp1 = new Employee(1, "Jayden", "Engineering", 75000.0);
    Employee emp2 = new Employee(2, "Ellis", "Sales", 50000.0);

    assertNotEquals(emp1.getId(), emp2.getId());
    assertNotEquals(emp1.getName(), emp2.getName());
  }
}
