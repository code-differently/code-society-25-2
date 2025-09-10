package com.codedifferently.lesson15;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class EmployeeTest {

  @Test
  void constructorAndGetters() {
    Employee e = new Employee(1, "Alice", "Engineering", 90000);
    assertEquals(1, e.getId());
    assertEquals("Alice", e.getName());
    assertEquals("Engineering", e.getDepartment());
    assertEquals(90000, e.getSalary());
    assertNull(e.getDetails());
  }

  @Test
  void settersUpdateFields() {
    Employee e = new Employee(0, null, null, 0);
    e.setId(7);
    e.setName("Bob");
    e.setDepartment("HR");
    e.setSalary(80000);
    assertEquals(7, e.getId());
    assertEquals("Bob", e.getName());
    assertEquals("HR", e.getDepartment());
    assertEquals(80000, e.getSalary());
  }

  @Test
  void setAndGetDetails() {
    Employee e = new Employee(2, "Cara", "Ops", 70000);
    e.setDetails("contract-to-hire");
    assertEquals("contract-to-hire", e.getDetails());
  }

  @Test
  void setDetailsWithNull() {
    Employee e = new Employee(3, "David", "Finance", 75000);
    e.setDetails("initial details");
    e.setDetails(null);
    assertNull(e.getDetails());
  }

  @Test
  void settersWithEdgeCases() {
    Employee e = new Employee(1, "Test", "Test", 50000);

    // Test negative ID
    e.setId(-1);
    assertEquals(-1, e.getId());

    // Test empty string name
    e.setName("");
    assertEquals("", e.getName());

    // Test null department
    e.setDepartment(null);
    assertNull(e.getDepartment());

    // Test zero salary
    e.setSalary(0);
    assertEquals(0, e.getSalary());

    // Test negative salary
    e.setSalary(-1000);
    assertEquals(-1000, e.getSalary());
  }

  @Test
  void constructorWithNullValues() {
    Employee e = new Employee(5, null, null, 55000);
    assertEquals(5, e.getId());
    assertNull(e.getName());
    assertNull(e.getDepartment());
    assertEquals(55000, e.getSalary());
    assertNull(e.getDetails());
  }

  @Test
  void constructorWithSpecialCharacters() {
    Employee e = new Employee(6, "José María", "R&D/QA", 65000.50);
    assertEquals(6, e.getId());
    assertEquals("José María", e.getName());
    assertEquals("R&D/QA", e.getDepartment());
    assertEquals(65000.50, e.getSalary());
  }

  @Test
  void multipleSettersTest() {
    Employee e = new Employee(8, "Initial", "Initial", 40000);

    e.setId(100);
    e.setName("Updated Name");
    e.setDepartment("Updated Dept");
    e.setSalary(95000);
    e.setDetails("Updated details");

    assertEquals(100, e.getId());
    assertEquals("Updated Name", e.getName());
    assertEquals("Updated Dept", e.getDepartment());
    assertEquals(95000, e.getSalary());
    assertEquals("Updated details", e.getDetails());
  }

  @Test
  void extremeSalaryValues() {
    Employee e = new Employee(9, "Test", "Test", 50000);

    // Test very large salary
    e.setSalary(Double.MAX_VALUE);
    assertEquals(Double.MAX_VALUE, e.getSalary());

    // Test very small positive salary
    e.setSalary(0.01);
    assertEquals(0.01, e.getSalary());
  }
}
