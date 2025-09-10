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
}
