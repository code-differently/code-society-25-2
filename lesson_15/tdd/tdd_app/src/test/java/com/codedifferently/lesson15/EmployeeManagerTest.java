package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeManagerTest {
  private EmployeeManager employees;
  private Employee testEmployee;

  @BeforeEach
  void setUp() {
    employees = new EmployeeManager();
    testEmployee = new Employee(1, "Bigger Thomas", "Aviation", 95000.0);
    employees.addEmployee(testEmployee);
  }

  @Test
  void addEmployeeCountTest() {
    int expected = 1;
    assertEquals(employees.getEmployeeCount(), expected);
  }

  @Test
  void getEmployeeTest() {
    Employee expected = testEmployee;
    assertEquals(employees.getEmployee(1), expected);
  }

  @Test
  void addEmployeeTest() {
    int expected = 1;
    int actual = employees.getEmployeeCount();
    assertEquals(actual, expected);
  }

  @Test
  void removeEmployeeTest() {
    employees.removeEmployee(testEmployee.getId());
    int expected = 0;
    int actual = employees.getEmployeeCount();
    assertEquals(actual, expected);
  }

  @Test
  void updateEmployeeTest() {
    testEmployee.setName("Little Thomas");
    testEmployee.setSalary(96000.0);
    employees.updateEmployee(testEmployee);
    String expected = "Little Thomas";
    String actual = employees.getEmployee(1).getName();

    double expected2 = 96000.0;
    double actual2 = employees.getEmployee(1).getSalary();
    assertEquals(actual, expected);
    assertEquals(actual2, expected2);
  }

  @Test
  void getAssertEmployeeCollectionTest() {
    assertThatThrownBy(
            () -> {
              employees.removeEmployee(3);
            })
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Employee does not in collection with id 3");
  }
}
