package com.codedifferently.lesson15;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeTest {
    private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = new Employee(2, "Jane Austen", "Design", 75000);
    }
    @Test
    public void getIdTest(){
        int expected = 2;
        assertEquals(employee.getId(), expected);
    }

    @Test
    public void setIdTest(){
        int expected = 32;
        employee.setId(32);
        assertEquals(employee.getId(), expected);
    }

    @Test
    public void getNameTest(){
        String expected = "Jane Austen";
        assertEquals(employee.getName(), expected);
    }
}