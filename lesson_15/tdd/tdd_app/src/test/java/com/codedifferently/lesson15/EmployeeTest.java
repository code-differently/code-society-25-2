package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    private Employee testEmployee;

    @BeforeEach
    void setUp() {
        testEmployee = new Employee(1, "John Doe", "Engineering", 75000.0);
    }

    @Test
    void testEmployeeConstructor() {
        Employee emp = new Employee(123, "Jane Smith", "HR", 65000.0);
        assertThat(emp).isNotNull();
        assertThat(emp.getId()).isEqualTo(123);
        assertThat(emp.getName()).isEqualTo("Jane Smith");
        assertThat(emp.getDepartment()).isEqualTo("HR");
        assertThat(emp.getSalary()).isEqualTo(65000.0);
    }

    @Test
    void testGetId() {
        assertThat(testEmployee.getId()).isEqualTo(1);
    }

    @Test
    void testSetId() {
        testEmployee.setId(999);
        assertThat(testEmployee.getId()).isEqualTo(999);
    }

    @Test
    void testGetName() {
        assertThat(testEmployee.getName()).isEqualTo("John Doe");
    }

    @Test
    void testSetName() {
        testEmployee.setName("Jane Updated");
        assertThat(testEmployee.getName()).isEqualTo("Jane Updated");
    }

    @Test
    void testGetDepartment() {
        assertThat(testEmployee.getDepartment()).isEqualTo("Engineering");
    }

    @Test
    void testSetDepartment() {
        testEmployee.setDepartment("Marketing");
        assertThat(testEmployee.getDepartment()).isEqualTo("Marketing");
    }

    @Test
    void testGetSalary() {
        assertThat(testEmployee.getSalary()).isEqualTo(75000.0);
    }

    @Test
    void testSetSalary() {
        testEmployee.setSalary(80000.0);
        assertThat(testEmployee.getSalary()).isEqualTo(80000.0);
    }

    @Test
    void testSetSalaryWithDecimal() {
        testEmployee.setSalary(75543.50);
        assertThat(testEmployee.getSalary()).isEqualTo(75543.50);
    }

    @Test
    void testAllFieldsCanBeUpdated() {
        testEmployee.setId(100);
        testEmployee.setName("Updated Name");
        testEmployee.setDepartment("Updated Department");
        testEmployee.setSalary(100000.0);

        assertThat(testEmployee.getId()).isEqualTo(100);
        assertThat(testEmployee.getName()).isEqualTo("Updated Name");
        assertThat(testEmployee.getDepartment()).isEqualTo("Updated Department");
        assertThat(testEmployee.getSalary()).isEqualTo(100000.0);
    }

    @Test
    void testConstructorWithZeroSalary() {
        Employee zeroSalaryEmployee = new Employee(2, "Intern", "IT", 0.0);
        assertThat(zeroSalaryEmployee.getSalary()).isEqualTo(0.0);
    }

    @Test
    void testConstructorWithNegativeId() {
        Employee negativeIdEmployee = new Employee(-1, "Test", "Test", 50000.0);
        assertThat(negativeIdEmployee.getId()).isEqualTo(-1);
    }

    @Test
    void testGetDetails() {
        Employee employee = new Employee(123, "John Doe", "Engineering", 75000.0);
        String expected = "Employee ID: 123, Name: John Doe, Department: Engineering, Salary: $75000.0";
        assertThat(employee.getDetails()).isEqualTo(expected);
    }

    @Test
    void testGetDetailsWithDifferentEmployee() {
        Employee employee = new Employee(456, "Jane Smith", "HR", 65000.5);
        String expected = "Employee ID: 456, Name: Jane Smith, Department: HR, Salary: $65000.5";
        assertThat(employee.getDetails()).isEqualTo(expected);
    }
}
