package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeTest {

  private Employee employee;

  @BeforeEach
  void setUp() {
    employee = new Employee(1, "John Doe", "Engineering", 75000);
  }

  @Test
  void testConstructor() {
    Employee emp = new Employee(2, "Jane Smith", "HR", 60000);

    assertThat(emp.getId()).isEqualTo(2);
    assertThat(emp.getName()).isEqualTo("Jane Smith");
    assertThat(emp.getDepartment()).isEqualTo("HR");
    assertThat(emp.getSalary()).isEqualTo(60000);
  }

  @Test
  void testGettersAndSetters() {
    employee.setName("Updated Name");
    employee.setDepartment("Updated Department");
    employee.setSalary(80000);

    assertThat(employee.getName()).isEqualTo("Updated Name");
    assertThat(employee.getDepartment()).isEqualTo("Updated Department");
    assertThat(employee.getSalary()).isEqualTo(80000);
  }

  @Test
  void testGetDetails() {
    String expected = "ID: 1, Name: John Doe, Department: Engineering, Salary: 75000.0";
    assertThat(employee.getDetails()).isEqualTo(expected);
  }

  @Test
  void testToString() {
    String expected = "ID: 1, Name: John Doe, Department: Engineering, Salary: $75000.0";
    assertThat(employee.toString()).isEqualTo(expected);
  }
}
