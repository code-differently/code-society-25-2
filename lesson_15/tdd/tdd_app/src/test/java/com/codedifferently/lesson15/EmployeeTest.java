package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class EmployeeTest {
    @Test
    void getDetails_shouldReturnCorrectString() {
        Employee emp = new Employee(1, "Alice", "Engineering", 90000.0);
        String expected = "Employee{id=1, name='Alice', department='Engineering', salary=90000.0}";
        assertThat(emp.getDetails()).isEqualTo(expected);
    }

    @Test
    void getDetails_shouldReflectUpdatedFields() {
        Employee emp = new Employee(2, "Bob", "HR", 50000.0);
        emp.setName("Robert");
        emp.setDepartment("Finance");
        emp.setSalary(60000.0);
        String expected = "Employee{id=2, name='Robert', department='Finance', salary=60000.0}";
        assertThat(emp.getDetails()).isEqualTo(expected);
    }

    @Test
    void constructor_shouldSetAllFields() {
        Employee emp = new Employee(3, "Carol", "Marketing", 75000.0);
        assertThat(emp.getId()).isEqualTo(3);
        assertThat(emp.getName()).isEqualTo("Carol");
        assertThat(emp.getDepartment()).isEqualTo("Marketing");
        assertThat(emp.getSalary()).isEqualTo(75000.0);
    }

    @Test
    void setters_shouldUpdateFields() {
        Employee emp = new Employee(4, "Dan", "Sales", 65000.0);
        emp.setId(5);
        emp.setName("Daniel");
        emp.setDepartment("Support");
        emp.setSalary(70000.0);
        assertThat(emp.getId()).isEqualTo(5);
        assertThat(emp.getName()).isEqualTo("Daniel");
        assertThat(emp.getDepartment()).isEqualTo("Support");
        assertThat(emp.getSalary()).isEqualTo(70000.0);
    }
}
