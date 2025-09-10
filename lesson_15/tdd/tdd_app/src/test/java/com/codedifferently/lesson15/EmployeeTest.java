/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
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
    employee = new Employee(1, "john Doe", "Engineering", 75000.0);
  }

  @Test
  public void testGetId01() {
    // Given

    // When
    int actual = employee.getId();
    int expected = 1;
    // Then
    assertEquals(expected, actual);
  }

  @Test
  public void testGetName01() {
    // Given

    // When
    String actual = employee.getName();
    String expected = "john Doe";
    // Then
    assertEquals(expected, actual);
  }

  @Test
  public void testGetDepartment01() {
    // Given
    // When
    String actual = employee.getDepartment();
    String expected = "Engineering";
    // Then
    assertEquals(expected, actual);
  }

  @Test
  public void testGetSalary01() {
    // Given
    // When
    Double actual = employee.getSalary();
    Double expected = 75000.0;
    // Then
    assertEquals(expected, actual);
  }

  @Test
  public void testSetId01() {
    // Given
    int expected = 2;
    // When
    employee.setId(expected);
    int actual = employee.getId();
    // Then
    assertEquals(expected, actual);
  }

  @Test
  public void testSetName01() {
    // Given
    String expected = "Jane Doe";
    // When
    employee.setName(expected);
    String actual = employee.getName();
    // Then
    assertEquals(expected, actual);
  }

  @Test
  public void testSetDepartment01() {
    // Given
    String expected = "Marketing";
    // When
    employee.setDepartment(expected);
    String actual = employee.getDepartment();
    // Then
    assertEquals(expected, actual);
  }

  @Test
  public void testSetSalary01() {
    // Given
    Double expected = 80000.0;
    // When
    employee.setSalary(expected);
    Double actual = employee.getSalary();
    // Then
    assertEquals(expected, actual);
  }

  @Test
  public void testGetDetails01() {
    // Given
    String expected = "ID: 1, Name: john Doe, Department: Engineering, Salary: 75000.0";
    // When
    String actual = employee.getDetails();
    // Then
    assertEquals(expected, actual);
  }
}
