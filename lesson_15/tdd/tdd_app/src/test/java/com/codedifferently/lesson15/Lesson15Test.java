package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;
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

  @Test
  public void checkForGetName() {
    Employee employee = new Employee(123, "John Doe", "Engineering", 75000.00);
    assertThat(employee.getName()).isEqualTo("John Doe");
  }

  @Test
  public void checkForGetDepartment() {
    Employee employee = new Employee(123, "John Doe", "Engineering", 75000.00);
    assertThat(employee.getDepartment()).isEqualTo("Engineering");
  }

  @Test
  public void testSetId() {
    Employee employee = new Employee(123, "John Doe", "Engineering", 75000.00);
    employee.setId(456);
    assertThat(employee.getId()).isEqualTo(456);
  }

  @Test
  public void testGetId() {
    Employee employee = new Employee(123, "John Doe", "Engineering", 75000.00);
    assertThat(employee.getId()).isEqualTo(123);
  }

  @Test
  public void testSetName() {
    Employee employee = new Employee(123, "John Doe", "Engineering", 75000.00);
    employee.setName("Jane Smith");
    assertThat(employee.getName()).isEqualTo("Jane Smith");
  }

  @Test
  public void testUpdateEmployee() {
    EmployeeManager manager = new EmployeeManager();
    Employee employee = new Employee(123, "John Doe", "Engineering", 75000.00);
    manager.addEmployee(employee);

    Employee updatedEmployee = new Employee(123, "John Doe", "Marketing", 80000.00);
    manager.updateEmployee(updatedEmployee);

    Employee retrievedEmployee = manager.getEmployee(123);
    assertThat(retrievedEmployee.getDepartment()).isEqualTo("Marketing");
    assertThat(retrievedEmployee.getSalary()).isEqualTo(80000.00);
  }

  @Test
  public void testGetDetails() {
    Employee employee = new Employee(123, "John Doe", "Engineering", 75000.00);
    assertThat(employee.getDetails())
        .isEqualTo("ID: 123, Name: John Doe, Department: Engineering, Salary: $75000.0");
  }

  @Test
  public void testEmployeeManager() {
    EmployeeManager manager = new EmployeeManager();
    Employee employee1 = new Employee(1, "Alice", "HR", 60000.00);
    Employee employee2 = new Employee(2, "Bob", "IT", 70000.00);

    manager.addEmployee(employee1);
    manager.addEmployee(employee2);

    assertThat(manager.getEmployeeCount()).isEqualTo(2);

    Employee retrievedEmployee = manager.getEmployee(1);
    assertThat(retrievedEmployee.getName()).isEqualTo("Alice");

    manager.removeEmployee(1);
    assertThat(manager.getEmployeeCount()).isEqualTo(1);
  }
}
