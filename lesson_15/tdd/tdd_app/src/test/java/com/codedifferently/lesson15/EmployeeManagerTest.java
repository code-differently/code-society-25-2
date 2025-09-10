package com.codedifferently.lesson15;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.codedifferently.lesson15.Employee;
import com.codedifferently.lesson15.EmployeeManager;



public class EmployeeManagerTest {
    private EmployeeManager employeeManager;
    private Employee employee;

    @BeforeEach
    public void setUp() {
        employeeManager = new EmployeeManager();
        employee = new Employee(2, "Jane Austen", "Design", 75000, "UX Designer", 5);
        employeeManager.addEmployee(employee);
}

    @Test 
    public void addEmployeeTest() {
        Employee employee = new Employee(1, "Adam Warlok", "Engineering", 100000, "Software Engineer", 3);
        employeeManager.addEmployee(employee);
        assertEquals(2, employeeManager.getEmployeeCount());
    }

    @Test 
    public void getEmployeeTest() {
        Employee employee = employeeManager.getEmployee(2);
        assertEquals("Jane Austen", employee.getName());
    }

    @Test
    public void updateEmployeeTest() {
        Employee employee = new Employee(2, "Jane Austen", "Product", 80000, "Product Owner", 5);
        employeeManager.updateEmployee(employee);
        assertEquals("Product", employee.getDepartment());
    }
}