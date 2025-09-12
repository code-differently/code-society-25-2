package test.java.com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.assertThat;

import com.codedifferently.lesson15.Employee;
import org.junit.jupiter.api.Test;

class EmployeeTest {
  @Test
  void constructor_setsAllFields() {
    Employee emp = new Employee(1, "Alice", "Engineering", 100000.0);
    assertThat(emp.getId()).isEqualTo(1);
    assertThat(emp.getName()).isEqualTo("Alice");
    assertThat(emp.getDepartment()).isEqualTo("Engineering");
    assertThat(emp.getSalary()).isEqualTo(100000.0);
  }

  @Test
  void setters_updateFields() {
    Employee emp = new Employee(0, "", "", 0.0);
    emp.setId(2);
    emp.setName("Bob");
    emp.setDepartment("HR");
    emp.setSalary(50000.0);
    assertThat(emp.getId()).isEqualTo(2);
    assertThat(emp.getName()).isEqualTo("Bob");
    assertThat(emp.getDepartment()).isEqualTo("HR");
    assertThat(emp.getSalary()).isEqualTo(50000.0);
  }

  @Test
  void handlesNegativeSalary() {
    Employee emp = new Employee(4, "Dan", "Finance", -1000.0);
    assertThat(emp.getSalary()).isEqualTo(-1000.0);
    emp.setSalary(-500.0);
    assertThat(emp.getSalary()).isEqualTo(-500.0);
  }

  @Test
  void handlesEmptyAndNullStrings() {
    Employee emp = new Employee(5, "", "", 0.0);
    assertThat(emp.getName()).isEmpty();
    assertThat(emp.getDepartment()).isEmpty();
    emp.setName("");
    emp.setDepartment("");
    assertThat(emp.getName()).isEmpty();
    assertThat(emp.getDepartment()).isEmpty();
  }

  @Test
  void handlesLargeId() {
    int largeId = Integer.MAX_VALUE;
    Employee emp = new Employee(largeId, "Eve", "IT", 120000.0);
    assertThat(emp.getId()).isEqualTo(largeId);
    emp.setId(Integer.MIN_VALUE);
    assertThat(emp.getId()).isEqualTo(Integer.MIN_VALUE);
  }

  @Test
  void getDetails_returnsExpectedString() {
    Employee emp = new Employee(10, "Jared Edge", "QA", 90000.0);
    String expected = "Employee{id=10, name='Jared Edge', department='QA', salary=90000.0}";
    assertThat(emp.getDetails()).isEqualTo(expected);
  }
}
