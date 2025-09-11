/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson15;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author vscode
 */
public class EmployeeTest {

  private Employee employee;

  @BeforeEach
  public void setUp() {
    // Given
    employee = new Employee(0, "Mattie", "Front end", 100000);
  }

  @Test
  public void getNameTest() {
    // Given
    var expected = "Mattie";
    // When
    String actual = employee.getName();

    // Then
    assertEquals(expected, actual);
  }

  @Test
  public void setNameTest() {
    employee.setName("Daniel");
    var expected = "Daniel";
    String actual = employee.getName();
    assertEquals(expected, actual);
  }

  @Test
  public void setId() {
    employee.setId(7);
    var expected = 7;
    int actual = employee.getId();
    assertEquals(expected, actual);
  }

  @Test
  public void setDepartment() {
    employee.setDepartment("Back end");
    var expected = "Back end";
    String actual = employee.getDepartment();
    assertEquals(expected, actual);
  }

  @Test
  public void setSalary() {
    employee.setSalary(200000.0);
    double expected = 200000.0;
    double actual = employee.getSalary();
    assertEquals(expected, actual);
  }

  @Test
  public void getId() {
    // given
    var expected = 0;
    // when
    int actual = employee.getId();

    // then
    assertEquals(expected, actual);
  }

  @Test
  public void getDepartment() {
    // given
    var expected = "Front end";
    // when
    String actual = employee.getDepartment();
    // then
    assertEquals(expected, actual);
  }

  @Test
  public void getSalary() {
    // given
    double expected = 100000.0;
    // when
    double actual = employee.getSalary();
    // then
    assertEquals(expected, actual);
  }
}
