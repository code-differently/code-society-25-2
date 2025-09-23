package com.codedifferently.lesson15;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeTest {
  private Employee employee;

  @BeforeEach
  public void setUp() {
    employee = new Employee(234, "Toni Morrison", "Classics", 800000);
  }

  @Test
  public void getIdTest() {
    int expected = 234;
    assertEquals(employee.getId(), expected);
  }

  @Test
  public void setIdTest() {
    int expected = 111;
    employee.setId(111);
    assertEquals(employee.getId(), expected);
  }

  @Test
  public void getNameTest() {
    String expected = "Toni Morrison";
    assertEquals(employee.getName(), expected);
  }

  @Test
  public void setNameTest() {
    String expected = "Richard Wright";
    employee.setName("Richard Wright");
    assertEquals(employee.getName(), expected);
  }

  @Test
  public void getDepartmentTest() {
    String expected = "Classics";
    assertEquals(employee.getDepartment(), expected);
  }

  @Test
  public void setDepartmentTest() {
    String expected = "Classics II";
    employee.setDepartment("Classics II");
    assertEquals(employee.getDepartment(), expected);
  }

  @Test
  public void getSalaryTest() {
    double expected = 800000;
    assertEquals(employee.getSalary(), expected);
  }

  @Test
  public void setSalaryTest() {
    double expected = 799999;
    employee.setSalary(799999);
    assertEquals(employee.getSalary(), expected);
  }

  @Test
  public void testGetDetails() {
    // Arrange - already done in @BeforeEach
    String expected = "Employee: Toni Morrison, Department: Classics, Salary: $800000.0";

    // Act
    String actual = employee.getDetails();

    // Assert
    assertEquals(expected, actual);
  }
}
