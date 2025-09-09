package com.codedifferently.lesson15;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author vscode
 */

public class EmployeeManagerTest {
    private Employee employee;
    private EmployeeManager employeeManager;


    @BeforeEach
    public void setUp() {
        employee = new Employee(7, "Brooklyn", "Potatoes", 10000.00);
        employeeManager = new EmployeeManager();
    }

    @Test
    public void addEmployeeTest(){
        //adds the employee
        employeeManager.addEmployee(employee);
        //checks if the id matches the employee manager id
        assertEquals(employeeManager.getEmployee(7), employee);
    }

    @Test
    public void updateEmployeeTest(){
        employeeManager.addEmployee(employee);
        String expected= "Harden";
        employee.setName(expected);
        employeeManager.updateEmployee(employee);
        assertEquals(employeeManager.getEmployee(7).getName(), expected);
    }

}
