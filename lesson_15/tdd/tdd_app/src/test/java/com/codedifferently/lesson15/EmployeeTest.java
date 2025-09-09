/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson15;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author vscode
 */
public class EmployeeTest {
    private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = new Employee(7, "Brooklyn", "potatoes", 10000.00);
    }
    
    
    @Test
    public void getIdTest(){
        int expected = 7;
        assertEquals(employee.getId(), expected);

    }

    @Test
    public void setIdTest(){
        // Given 
        int expected = 9;
        //WHen 
        employee.setId(9);
        // Then
        assertEquals(employee.getId(), expected);
    }

    @Test
    public void getName(){
        //Given
        String expected = "Brooklyn";
        //Then
        assertEquals(employee.getName(), expected);

    }
}

