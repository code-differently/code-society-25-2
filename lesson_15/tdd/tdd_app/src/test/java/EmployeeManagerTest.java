package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeManagerTest {

    private EmployeeManager employeeManager;
    private Employee testEmployee;

    @BeforeEach
    private Map<Integer, Employee> employees = new HashMap<>();

    public void addEmployee(Employee employee) {
        employees.put(employee.getId(), employee);
    }

    public Employee getEmployee(int id) {
        return employees.get(id);
    }

    public void removeEmployee(int id) {
        employees.remove(id);
    }

    public int getEmployeeCount() {
        return employees.size();
    }
}
 @Test
    void testAddAndGetEmployee() {
        EmployeeManager manager = new EmployeeManager();
        Employee emp = new Employee(1, "Alice", "Engineering", 75000);

        manager.addEmployee(emp);

        Employee retrieved = manager.getEmployee(1);
        assertNotNull(retrieved);
        assertEquals("Alice", retrieved.getName());
        assertEquals("Engineering", retrieved.getDepartment());
        assertEquals(75000, retrieved.getSalary());
    }

    @Test
    void testRemoveEmployee() {
        EmployeeManager manager = new EmployeeManager();
        Employee emp = new Employee(2, "Bob", "HR", 60000);

        manager.addEmployee(emp);
        assertEquals(1, manager.getEmployeeCount());

        manager.removeEmployee(2);
        assertEquals(0, manager.getEmployeeCount());
        assertNull(manager.getEmployee(2));
    }
}