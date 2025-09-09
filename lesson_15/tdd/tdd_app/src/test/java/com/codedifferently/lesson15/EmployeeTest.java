package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeTest {

  private Employee employee;

  @BeforeEach
  void setUp() {
    employee = new Employee(1, "John Doe", "Engineering", 75000.0);
  }

  @Test
  void testEmployeeCreation() {
    assertThat(employee).isNotNull();
  }

  @Test
  void testGetId() {
    assertThat(employee.getId()).isEqualTo(1);
  }

  @Test
  void testGetName() {
    assertThat(employee.getName()).isEqualTo("John Doe");
  }

  @Test
  void testGetDetails() {
    // TDD Step 1: Write the test FIRST (this should fail initially)
    String expectedDetails =
        "Employee ID: 1, Name: John Doe, Department: Engineering, Salary: $75000.0";
    String actualDetails = employee.getDetails();
    assertThat(actualDetails).isEqualTo(expectedDetails);
  }

  @Test
  void testGetDepartment() {
    assertThat(employee.getDepartment()).isEqualTo("Engineering");
  }

  @Test
  void testGetSalary() {
    assertThat(employee.getSalary()).isEqualTo(75000.0);
  }

  @Test
  void testSetId() {
    employee.setId(999);
    assertThat(employee.getId()).isEqualTo(999);
  }

  @Test
  void testSetName() {
    employee.setName("Jane Smith");
    assertThat(employee.getName()).isEqualTo("Jane Smith");
  }

  @Test
  void testSetDepartment() {
    employee.setDepartment("Marketing");
    assertThat(employee.getDepartment()).isEqualTo("Marketing");
  }

  @Test
  void testSetSalary() {
    employee.setSalary(80000.0);
    assertThat(employee.getSalary()).isEqualTo(80000.0);
  }
}
