package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codedifferently.lesson15.Employee;



public class EmployeeTest {
  
  private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee(1, "Alice", "Engineering", 75000.0, "Software Engineering");
    }

    @Test
    public void testGetters() {
        assertEquals(1, employee.getId());
        assertEquals("Alice", employee.getName());
        assertEquals("Engineering", employee.getDepartment());
        assertEquals(75000.0, employee.getSalary());
        assertEquals("Software Engineering", employee.getDetails());
    }

    @Test 
    public void testSetters() {
        employee.setId(1);
        assertEquals(1, employee.getId());

        employee.setName("Alice");
        assertEquals("Alice", employee.getName());

        employee.setDepartment("Engineering");
        assertEquals("Engineering", employee.getDepartment());

        employee.setSalary(75000.0);
        assertEquals(75000.0, employee.getSalary());

        employee.getDetails("Software Engineering");
        assertEquals("Software Engineering", employee.getDetails());
    }
  }

  