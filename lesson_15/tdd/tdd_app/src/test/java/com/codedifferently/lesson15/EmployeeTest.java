package com.codedifferently.lesson15;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeTest {
  private Employee employee;

  @BeforeEach
  public void setUp() {
    employee = new Employee(2, "Jane Austen", "Design", 75000, "UX Designer", 5);
  }

  @Test
  public void getIdTest() {
    int expected = 2;
    assertEquals(employee.getId(), expected);
  }

  @Test
  public void setIdTest() {
    int expected = 32;
    employee.setId(32);
    assertEquals(employee.getId(), expected);
  }

  @Test
  public void getNameTest() {
    String expected = "Jane Austen";
    assertEquals(employee.getName(), expected);
  }

  @Test
  public void setNameTest() {
    String expected = "Bob Belcher";
    employee.setName("Bob Belcher");
    assertEquals(employee.getName(), expected);
  }

  @Test
  public void getDepartmentTest() {
    String expected = "Design";
    assertEquals(employee.getDepartment(), expected);
  }

  @Test
  public void setDepartmentTest() {
    String expected = "Product";
    employee.setDepartment("Product");
    assertEquals(employee.getDepartment(), expected);
  }

  @Test
  public void getSalaryTest() {
    double expected = 75000;
    assertEquals(employee.getSalary(), expected);
  }

  @Test
  public void setSalaryTest() {
    double expected = 99000;
    employee.setSalary(99000);
    assertEquals(employee.getSalary(), expected);
  }

  @Test
  public void getDetailsTest() {
    String expected = "UX Designer";
    assertEquals(employee.getDetails(), expected);
  }
   

  @Test
  public void setDetailsTest() {
    String expected = "UX Researcher";
    employee.setDetails("UX Researcher");
    assertEquals(employee.getDetails(), expected);
  }
  

  @Test
  public void getFloorTest() {
    int expected = 5;
    assertEquals(employee.getFloor(), expected);
  }

  public void setFloorTest() {
    int expected = 3;
    employee.setFloor(3);
    assertEquals(employee.getFloor(), expected);
  }

}
