package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeTest {
  private Employee emp;

  @BeforeEach
  public void setUp() {
    emp = new Employee(1, "Trinitie Jackson", "Motion Media", 75000.0);
  }

  @Test
  public void testGetSalary() {
    assertThat(emp.getSalary()).isEqualTo(75000.0);
  }

  @Test
  public void testSetSalary() {
    emp.setSalary(80000.0);
    assertThat(emp.getSalary()).isEqualTo(80000.0);
  }

  @Test
  public void testGetId() {
    assertThat(emp.getId()).isEqualTo(1);
  }

  @Test
  public void testSetId() {
    emp.setId(2);
    assertThat(emp.getId()).isEqualTo(2);
  }

  @Test
  public void testGetName() {
    assertThat(emp.getName()).isEqualTo("Trinitie Jackson");
  }

  @Test
  public void testSetName() {
    emp.setName("Trinitie Jackson");
    assertThat(emp.getName()).isEqualTo("Trinitie Jackson");
  }

  @Test
  public void testGetDepartment() {
    assertThat(emp.getDepartment()).isEqualTo("Motion Media");
  }

  @Test
  public void testSetDepartment() {
    emp.setDepartment("Motion Media");
    assertThat(emp.getDepartment()).isEqualTo("Motion Media");
  }

  @Test
  public void testGetDetails() {
    assertThat(emp.getDetails()).isEqualTo("ID: 1, Name: Trinitie Jackson, Department: Motion Media, Salary: 75000.0");
  }
}
