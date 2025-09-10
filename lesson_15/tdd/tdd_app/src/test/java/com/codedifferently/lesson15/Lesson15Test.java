package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Lesson15Test {

  @Test
  public void testLesson15() {
    assertThat(new Lesson15()).isNotNull();
  }

  @Test
  public void testGetGreeting() {
    // Act
    Lesson15.main(null);
  }

  // Employee Tests
  @Test
  public void testGetIdReturnsCorrectValue() {
    Employee employee = new Employee(123, "John Doe", "Engineering", 75000.0);
    int id = employee.getId();
    assertThat(id).isEqualTo(123);
  }

  @Test
  public void testSetIdUpdatesValue() {
    Employee employee = new Employee(123, "John Doe", "Engineering", 75000.0);
    employee.setId(456);
    int id = employee.getId();
    assertThat(id).isEqualTo(456);
  }

  @Test
  public void testGetNameReturnsCorrectValue() {
    Employee employee = new Employee(123, "John Doe", "Engineering", 75000.0);
    String name = employee.getName();
    assertThat(name).isEqualTo("John Doe");
  }

  @Test
  public void testSetNameUpdatesValue() {
    Employee employee = new Employee(123, "John Doe", "Engineering", 75000.0);
    employee.setName("Jane Smith");
    String name = employee.getName();
    assertThat(name).isEqualTo("Jane Smith");
  }

  @Test
  public void testSetNameWithNullValue() {
    Employee employee = new Employee(123, "John Doe", "Engineering", 75000.0);
    employee.setName(null);
    String name = employee.getName();
    assertThat(name).isNull();
  }

  @Test
  public void testGetDepartmentReturnsCorrectValue() {
    Employee employee = new Employee(123, "John Doe", "Engineering", 75000.0);
    String department = employee.getDepartment();
    assertThat(department).isEqualTo("Engineering");
  }

  @Test
  public void testSetDepartmentUpdatesValue() {
    Employee employee = new Employee(123, "John Doe", "Engineering", 75000.0);
    employee.setDepartment("Marketing");
    String department = employee.getDepartment();
    assertThat(department).isEqualTo("Marketing");
  }

  @Test
  public void testSetDepartmentWithNullValue() {
    Employee employee = new Employee(123, "John Doe", "Engineering", 75000.0);
    employee.setDepartment(null);
    String department = employee.getDepartment();
    assertThat(department).isNull();
  }

  @Test
  public void testGetSalaryReturnsCorrectValue() {
    Employee employee = new Employee(123, "John Doe", "Engineering", 75000.0);
    double salary = employee.getSalary();
    assertThat(salary).isEqualTo(75000.0);
  }

  @Test
  public void testSetSalaryUpdatesValue() {
    Employee employee = new Employee(123, "John Doe", "Engineering", 75000.0);
    employee.setSalary(85000.0);
    double salary = employee.getSalary();
    assertThat(salary).isEqualTo(85000.0);
  }

  @Test
  public void testSetSalaryWithZeroValue() {
    Employee employee = new Employee(123, "John Doe", "Engineering", 75000.0);
    employee.setSalary(0.0);
    double salary = employee.getSalary();
    assertThat(salary).isEqualTo(0.0);
  }

  @Test
  public void testSetSalaryWithNegativeValue() {
    Employee employee = new Employee(123, "John Doe", "Engineering", 75000.0);
    employee.setSalary(-1000.0);
    double salary = employee.getSalary();
    assertThat(salary).isEqualTo(-1000.0);
  }

  private EmployeeManager manager;
  private Employee testEmployee;

  @BeforeEach
  void setUp() {
    manager = new EmployeeManager();
    testEmployee = new Employee(1, "John Doe", "Engineering", 75000.0);
  }

  // EmployeeManager Tests
  @Test
  void testAddEmployeeIncreasesCount() {
    manager.addEmployee(testEmployee);
    assertThat(manager.getEmployeeCount()).isEqualTo(1);
  }

  @Test
  void testGetEmployeeReturnsCorrectEmployee() {
    manager.addEmployee(testEmployee);
    Employee retrieved = manager.getEmployee(1);
    assertThat(retrieved).isEqualTo(testEmployee);
    assertThat(retrieved.getName()).isEqualTo("John Doe");
  }

  @Test
  void testGetEmployeeThrowsExceptionWhenNotFound() {
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> manager.getEmployee(999));
    assertThat(exception.getMessage()).contains("Employee does not in collection with id 999");
  }

  @Test
  void testUpdateEmployeeModifiesExistingEmployee() {
    manager.addEmployee(testEmployee);
    Employee updatedEmployee = new Employee(1, "John Smith", "Marketing", 80000.0);

    manager.updateEmployee(updatedEmployee);
    Employee retrieved = manager.getEmployee(1);

    assertThat(retrieved.getName()).isEqualTo("John Smith");
    assertThat(retrieved.getDepartment()).isEqualTo("Marketing");
    assertThat(retrieved.getSalary()).isEqualTo(80000.0);
  }

  @Test
  void testUpdateEmployeeThrowsExceptionWhenNotFound() {
    Employee nonExistentEmployee = new Employee(999, "Nobody", "Nowhere", 0.0);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> manager.updateEmployee(nonExistentEmployee));
    assertThat(exception.getMessage()).contains("Employee does not in collection with id 999");
  }

  @Test
  void testRemoveEmployeeDecreasesCount() {
    manager.addEmployee(testEmployee);
    assertThat(manager.getEmployeeCount()).isEqualTo(1);

    manager.removeEmployee(1);
    assertThat(manager.getEmployeeCount()).isEqualTo(0);
  }

  @Test
  void testRemoveEmployeeThrowsExceptionWhenNotFound() {
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> manager.removeEmployee(999));
    assertThat(exception.getMessage()).contains("Employee does not in collection with id 999");
  }

  @Test
  void testGetEmployeeCountReturnsZeroForEmptyManager() {
    EmployeeManager emptyManager = new EmployeeManager();
    assertThat(emptyManager.getEmployeeCount()).isEqualTo(0);
  }

  @Test
  void testMultipleEmployeesHandledCorrectly() {
    Employee emp1 = new Employee(1, "Alice", "HR", 60000.0);
    Employee emp2 = new Employee(2, "Bob", "IT", 70000.0);
    Employee emp3 = new Employee(3, "Charlie", "Finance", 65000.0);

    manager.addEmployee(emp1);
    manager.addEmployee(emp2);
    manager.addEmployee(emp3);

    assertThat(manager.getEmployeeCount()).isEqualTo(3);
    assertThat(manager.getEmployee(1).getName()).isEqualTo("Alice");
    assertThat(manager.getEmployee(2).getName()).isEqualTo("Bob");
    assertThat(manager.getEmployee(3).getName()).isEqualTo("Charlie");
  }

  @Test
  void testEmployeeGetDetailsReturnsCorrectInfo() {
    Employee employee = new Employee(1, "John Doe", "Engineering", 75000.0);
    String details = employee.getDetails();
    assertThat(details)
        .isEqualTo("Employee[id=1, name=John Doe, department=Engineering, salary=75000.0]");
  }
}
