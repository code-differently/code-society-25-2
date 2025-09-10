package test.java.com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.*;

import com.codedifferently.lesson15.Employee;
import com.codedifferently.lesson15.EmployeeManager;
import org.junit.jupiter.api.Test;

class EmployeeManagerTest {
  @Test
  void addAndGetEmployee_works() {
    EmployeeManager manager = new EmployeeManager();
    Employee emp = new Employee(1, "Alice", "Engineering", 100000.0);
    manager.addEmployee(emp);
    assertThat(manager.getEmployee(1)).isEqualTo(emp);
    assertThat(manager.getEmployeeCount()).isEqualTo(1);
  }

  @Test
  void updateEmployee_works() {
    EmployeeManager manager = new EmployeeManager();
    Employee emp = new Employee(2, "Bob", "HR", 50000.0);
    manager.addEmployee(emp);
    Employee updated = new Employee(2, "Bob", "HR", 60000.0);
    manager.updateEmployee(updated);
    assertThat(manager.getEmployee(2).getSalary()).isEqualTo(60000.0);
  }

  @Test
  void removeEmployee_works() {
    EmployeeManager manager = new EmployeeManager();
    Employee emp = new Employee(3, "Carol", "Marketing", 75000.0);
    manager.addEmployee(emp);
    manager.removeEmployee(3);
    assertThat(manager.getEmployeeCount()).isEqualTo(0);
    assertThatThrownBy(() -> manager.getEmployee(3)).isInstanceOf(IllegalArgumentException.class);
  }
}
