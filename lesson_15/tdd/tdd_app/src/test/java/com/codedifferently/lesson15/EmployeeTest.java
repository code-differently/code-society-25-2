package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeTest {
  Employee employee;

  @BeforeEach
  public void setup() {
    employee = new Employee(1, "Jane Doe", "HR", 50000.00);
  }

  @Test
  public void testEmployee() {
    assertThat(employee).isNotNull();
  }

  @Test
  public void testGetID() {
    assertThat(employee.getId()).isEqualTo(1);
  }

  @Test
  public void testGetName() {
    assertThat(employee.getName()).isEqualTo("Jane Doe");
  }

  @Test
  public void testGetDepartment() {
    assertThat(employee.getDepartment()).isEqualTo("HR");
  }

  @Test
  public void testGetSalary() {
    assertThat(employee.getSalary()).isEqualTo(50000.00);
  }

  @Test
  public void testSetID() {
    employee.setId(2);
    assertThat(employee.getId()).isEqualTo(2);
  }

  @Test
  public void testSetName() {
    employee.setName("John Smith");
    assertThat(employee.getName()).isEqualTo("John Smith");
  }

  @Test
  public void testSetDepartment() {
    employee.setDepartment("Finance");
    assertThat(employee.getDepartment()).isEqualTo("Finance");
  }

  @Test
  public void testSetSalary() {
    employee.setSalary(60000.00);
    assertThat(employee.getSalary()).isEqualTo(60000.00);
  }

  @Test
  public void testDetails() {
    String expected = "ID: 1;" + "Jane Doe in HR who makes 50000.00 per year.";
    assertThat(employee.getDetails()).isEqualTo(expected);
  }
}
