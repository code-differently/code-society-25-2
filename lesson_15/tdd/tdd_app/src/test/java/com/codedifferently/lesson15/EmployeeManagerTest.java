package com.codedifferently.lesson15;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeManagerTest {

  private EmployeeManager manager;
  private Employee alice;
  private Employee bob;

  @BeforeEach
  void setUp() {
    manager = new EmployeeManager();
    alice = new Employee(1, "Alice", "Engineering", 90000.0);
    bob = new Employee(2, "Bob", "HR", 80000.0);
  }

  @Test
  void addAndGetEmployee_returnsEmployee() {
    manager.addEmployee(alice);

    Employee result = manager.getEmployee(1);

    assertEquals("Alice", result.getName());
    assertEquals("Engineering", result.getDepartment());
    assertEquals(90000.0, result.getSalary());
    assertEquals(1, manager.getEmployeeCount());
  }

  @Test
  void updateEmployee_overwritesExistingRecord() {
    manager.addEmployee(alice);
    // update salary + name
    Employee updated = new Employee(1, "Alice A.", "Engineering", 95000.0);

    manager.updateEmployee(updated);

    Employee result = manager.getEmployee(1);
    assertEquals("Alice A.", result.getName());
    assertEquals(95000.0, result.getSalary());
    assertEquals(1, manager.getEmployeeCount()); // still one employee
  }

  @Test
  void removeEmployee_deletesRecord() {
    manager.addEmployee(alice);
    assertEquals(1, manager.getEmployeeCount());

    manager.removeEmployee(1);

    assertEquals(0, manager.getEmployeeCount());
    assertThrows(IllegalArgumentException.class, () -> manager.getEmployee(1));
  }

  @Test
  void getEmployeeCount_tracksAddsAndRemoves() {
    assertEquals(0, manager.getEmployeeCount());
    manager.addEmployee(alice);
    manager.addEmployee(bob);
    assertEquals(2, manager.getEmployeeCount());

    manager.removeEmployee(1);
    assertEquals(1, manager.getEmployeeCount());
  }

  // ---- Exception paths (exercise the false branch of assertEmployeeInCollection) ----

  @Test
  void getEmployee_throwsWhenMissing() {
    assertThrows(IllegalArgumentException.class, () -> manager.getEmployee(99));
  }

  @Test
  void updateEmployee_throwsWhenMissing() {
    // no add first → should throw
    assertThrows(IllegalArgumentException.class, () -> manager.updateEmployee(alice));
  }

  @Test
  void removeEmployee_throwsWhenMissing() {
    assertThrows(IllegalArgumentException.class, () -> manager.removeEmployee(99));
  }

  // ---- Edge case: adding the same id twice (exercises put-overwrite path) ----
  @Test
  void addEmployee_sameId_overwritesPrevious() {
    manager.addEmployee(alice); // id = 1
    Employee replace = new Employee(1, "Alice Replaced", "Eng", 91000.0);

    manager.addEmployee(replace); // Map.put should overwrite

    Employee result = manager.getEmployee(1);
    assertEquals("Alice Replaced", result.getName());
    assertEquals(91000.0, result.getSalary());
    assertEquals(1, manager.getEmployeeCount());
  }
}
