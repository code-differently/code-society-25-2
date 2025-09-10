package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class EmployeeTest {
    @Test
    void getDetails_returnsCorrectString() {
        Employee emp = new Employee(1, "Alice", "Engineering", 95000.0);
        String details = emp.getDetails();
        assertThat(details).isEqualTo("Employee{id=1, name='Alice', department='Engineering', salary=95000.0}");
    }

    @Test
    void getDetails_handlesDifferentValues() {
        Employee emp = new Employee(2, "Bob", "HR", 50000.0);
        String details = emp.getDetails();
        assertThat(details).isEqualTo("Employee{id=2, name='Bob', department='HR', salary=50000.0}");
    }
}
