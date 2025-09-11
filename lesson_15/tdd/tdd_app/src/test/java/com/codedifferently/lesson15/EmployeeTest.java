package com.codedifferently.lesson15;

import org.junit.jupiter.api.Test;

import com.codedifferently.lesson15.Employee;

public class EmployeeTest {
    private Employee employee;

    @BeforeEach
    public void setUp(){
        employee = new Employee(234, "Toni Morrison", "Classics", 800000, "The Song of Solomon");
 
    }
    @Test
    public void getIdTest(){
        int expected = 234;
        assertEquals(employee.getId(),expected);
    }
@Test 
public void setIdTest(){
    int expected = 111;
    employee.setId (111);
    assertEquals(employee.getId(),expected);

}
@Test 
public void getNameTest(){
    String expected = "Toni Morrison";
    assertEquals(employee.getName(), expected);
}
@Test 
public void setNameTest (){
    String expected = "Richard Wright";
    employee.setName ("Richard Wright");
    assertEquals(employee.getName(), expected);

}
@Test 
public void getDepartmentTest(){
    String expected = "Classics";
    assertEquals(employee.getDepartment(), expected);
}
@Test 
public void setDepartmentTest(){
    String expected = "Classics II";
    employee.setDepartment("Classics II");
    assertEquals (employee.getDepartment(), expected);

}
@Test 
public void getSalaryTest(){
    double expected = 800000;
    assertEquals(employee.getSalary(), expected);
}
@Test 
public void setSalaryTest(){
    double expected = 799999;
    employee.setSalary(799999);
    assertEquals(employee.getSalary(), expected);
}
@Test 
public void getDetailsTest(){
    String expected = "The Song of Solomon";
    assertEquals(employee.getDetails(), expected);
}
@Test 
public void setDetailsTest(){
    String expected = "Native Son";
    employee.setDetails("Native Son");
    assertEquals(employee.getDetails(), expected);
}
}
