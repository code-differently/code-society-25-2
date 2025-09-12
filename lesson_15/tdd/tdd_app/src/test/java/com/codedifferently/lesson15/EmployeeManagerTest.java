package com.codedifferently.lesson15;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeManagerTest {


  private EmployeeManager manager;
  private Employee employee;

  @BeforeEach
  void setUp() {
    manager = new EmployeeManager();
    employee =
        new Employee(1, "Jayden", "Developer", 100000); // assuming Employee has (id, name, role)
  }

  @Test
  void testAddEmployeeIncreasesCount() {
    manager.addEmployee(employee);
    assertEquals(1, manager.getEmployeeCount());
  }

  @Test
  void testGetEmployeeReturnsCorrectEmployee() {
    manager.addEmployee(employee);
    Employee found = manager.getEmployee(1);
    assertEquals("Jayden", found.getName());
    assertEquals("Developer", found.getDepartment());
  }

  @Test
  void testUpdateEmployeeChangesData() {
    manager.addEmployee(employee);
    Employee updated = new Employee(1, "Jayden", "Manager", 200000);
    manager.updateEmployee(updated);

    Employee found = manager.getEmployee(1);
    assertEquals("Manager", found.getDepartment());
  }

  @Test
  void testRemoveEmployeeDecreasesCount() {
    manager.addEmployee(employee);
    manager.removeEmployee(1);

    assertEquals(0, manager.getEmployeeCount());
  }

  @Test
  void testGetEmployeeThrowsIfNotFound() {
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              manager.getEmployee(999);
            });
    assertTrue(exception.getMessage().contains("Employee does not in collection"));
  }

  @Test
  void testUpdateEmployeeThrowsIfNotFound() {
    Employee ghost = new Employee(2, "Ghost", "Phantom", 0.0);
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          manager.updateEmployee(ghost);
        });
  }

  @Test
  void testRemoveEmployeeThrowsIfNotFound() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          manager.removeEmployee(5);
        });
  }
}
