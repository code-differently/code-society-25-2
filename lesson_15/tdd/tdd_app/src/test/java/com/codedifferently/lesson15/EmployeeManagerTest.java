/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson15;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author vscode
 */
public class EmployeeManagerTest {
  private EmployeeManager employeeManager;

  @BeforeEach
  public void setUp() {
    employeeManager = new EmployeeManager();
    Employee employee = new Employee(1, "John Doe", "Engineering", 75000.0);
    employeeManager.addEmployee(employee);
  }

  @Test
  public void testAddEmployee() {
    // Given
    Employee employee = new Employee(2, "Jane Doe", "Marketing", 80000.0);
    // When
    employeeManager.addEmployee(employee);
    // Then
    assertEquals(2, employeeManager.getEmployeeCount());
  }

  @Test
  public void testGetEmployee() {
    // Given
    Employee employee = new Employee(1, "John Doe", "Engineering", 75000.0);
    employeeManager.addEmployee(employee);
    // When
    Employee retrieved = employeeManager.getEmployee(1);
    // Then
    assertEquals(employee, retrieved);
  }

  @Test
  public void testUpdateEmployee() {
    // Given
    Employee employee = new Employee(1, "John Doe", "Engineering", 75000.0);
    employeeManager.addEmployee(employee);
    Employee updatedEmployee = new Employee(1, "John Doe", "Engineering", 80000.0);
    // When
    employeeManager.updateEmployee(updatedEmployee);
    // Then
    assertEquals(updatedEmployee, employeeManager.getEmployee(1));
  }

  @Test
  public void testRemoveEmployee() {
    // Given

    // When
    employeeManager.removeEmployee(1);
    // Then
    assertThrows(IllegalArgumentException.class, () -> employeeManager.getEmployee(1));
  }

  @Test
  public void testGetEmployeeCount() {
    // Given
    employeeManager.addEmployee(new Employee(2, "Jane Doe", "Marketing", 80000.0));
    // When
    int count = employeeManager.getEmployeeCount();
    // Then
    assertEquals(2, count);
  }
}
