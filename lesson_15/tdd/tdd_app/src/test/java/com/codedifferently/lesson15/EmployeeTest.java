package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeTest {

  Employee employee;

  @BeforeEach
  void setUp() {
    employee = new Employee(1, "Alice", "Engineering", 75000.0);
  }

  @Test
  void testGetId() {
    assertThat(employee.getId()).isEqualTo(1);
  }

  @Test
  void testSetId() {
    employee.setId(2);
    assertThat(employee.getId()).isEqualTo(2);
  }

  @Test
  void testGetName() {
    assertThat(employee.getName()).isEqualTo("Alice");
  }

  @Test
  void testSetName() {
    employee.setName("Bob");
    assertThat(employee.getName()).isEqualTo("Bob");
  }

  @Test
  void testGetDepartment() {
    assertThat(employee.getDepartment()).isEqualTo("Engineering");
  }

  @Test
  void testSetDepartment() {
    employee.setDepartment("HR");
    assertThat(employee.getDepartment()).isEqualTo("HR");
  }

  @Test
  void testGetSalary() {
    assertThat(employee.getSalary()).isEqualTo(75000.0);
  }

  @Test
  void testSetSalary() {
    employee.setSalary(80000.0);
    assertThat(employee.getSalary()).isEqualTo(80000.0);
  }

  @Test
  void testGetDetails() {
    employee.setDetails();
    String expected = "ID: 1, Name: Alice, Department: Engineering, Salary: 75000.0";
    assertThat(employee.getDetails()).isEqualTo(expected);
  }

  @Test
  void testSetDetails() {
    employee.setId(3);
    employee.setName("Charlie");
    employee.setDepartment("Sales");
    employee.setSalary(90000.0);
    employee.setDetails();
    String expected = "ID: 3, Name: Charlie, Department: Sales, Salary: 90000.0";
    assertThat(employee.getDetails()).isEqualTo(expected);
  }

  @Test
  void testSetandGetDetailsWithEmptyValues() {
    employee.setName("");
    employee.setDepartment("");
    employee.setDetails();
    String expected = "ID: 1, Name: , Department: , Salary: 75000.0";
    assertThat(employee.getDetails()).isEqualTo(expected);
  }
}
