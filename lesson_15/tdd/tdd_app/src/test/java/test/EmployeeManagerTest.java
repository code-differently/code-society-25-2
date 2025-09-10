package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.codedifferently.lesson15.Employee;
import com.codedifferently.lesson15.EmployeeManager;

public class EmployeeManagerTest {
    private EmployeeManager manager;
    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee(1, "Alice", "Engineering", 75000.0, "Software Engineering");
        manager = new EmployeeManager();
        manager.addEmployee(employee);
    }

        @Test
    public void testAddEmployee() {
        Employee newEmp = new Employee(2, "Bob", "Sales", 65000.0, "Sales Manager");
        manager.addEmployee(newEmp);
        assertTrue(manager.getEmployee(2).equals(newEmp));
    }
    
    @Test
    public void testRemoveEmployee() {
        manager.removeEmployee(employee.getId());
        assertTrue(manager.getEmployeeCount() == 0);
    }
    
    @Test
    public void testFindEmployeeById() {
        Employee found = manager.findEmployeeById(employee.getId());
        assertEquals(employee, found);
    } 
    
}
