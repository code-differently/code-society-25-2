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
  }

  @Test
  public void addEmployeeTest() {
    // given
    var expected = new Employee(0, "Mattie", "Front end", 1000000);

    // when
    employeeManager.addEmployee(expected);
    Employee actual = employeeManager.getEmployee(0);

    // then
    assertEquals(expected, actual);
  }

  @Test
  public void updateEmployee() {
    // given
    var current = new Employee(0, "Mattie", "Front end", 1000000);
    employeeManager.addEmployee(current);

    var expected = new Employee(0, "Mattie", "Front end", 200000);

    // when
    employeeManager.updateEmployee(expected);
    Employee actual = employeeManager.getEmployee(0);

    // then
    assertEquals(expected, actual);
  }

  @Test
  public void deleteEmployee() {
    // given
    var current = new Employee(0, "Mattie", "Front end", 200000);
    employeeManager.addEmployee(current);

    // when
    employeeManager.removeEmployee(current.getId());
    // then
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> employeeManager.getEmployee(current.getId()));

    assertEquals("Employee does not in collection with id 0", exception.getMessage());
  }
}
