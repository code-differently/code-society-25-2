package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeManagerTest {

  private EmployeeManager manager;
  private Employee employee1;
  private Employee employee2;

  @BeforeEach
  public void setUp() {
    manager = new EmployeeManager();
    employee1 = new Employee(1, "Trinitie Jackson", "Motion Media", 75000.0);
    employee2 = new Employee(2, "Jane Smith", "Marketing", 65000.0);
  }

  @Test
  public void testAddAndGetEmployee() {
    manager.addEmployee(employee1);
    Employee retrieved = manager.getEmployee(1);
    assertThat(retrieved).isEqualTo(employee1);
    assertThat(manager.getEmployeeCount()).isEqualTo(1);
  }

  @Test
  public void testUpdateEmployee() {
    manager.addEmployee(employee1);
    Employee updatedEmployee = new Employee(1, "Trinitie Jackson", "Motion Media", 80000.0);
    manager.updateEmployee(updatedEmployee);
    assertThat(manager.getEmployee(1).getSalary()).isEqualTo(80000.0);
  }

  @Test
  public void testRemoveEmployee() {
    manager.addEmployee(employee1);
    manager.removeEmployee(1);
    assertThat(manager.getEmployeeCount()).isEqualTo(0);
    assertThrows(IllegalArgumentException.class, () -> manager.getEmployee(1));
  }

  @Test
  public void testGetEmployeeCount() {
    manager.addEmployee(employee1);
    manager.addEmployee(employee2);
    assertThat(manager.getEmployeeCount()).isEqualTo(2);
  }
}
