package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EmployeeManagerTest {

  @Test
  void add_get_update_remove_employee_flow() {
    EmployeeManager manager = new EmployeeManager();
    Employee e = new Employee(1, "Key Brown", "Support", 52000.0);

    // add + get
    manager.addEmployee(e);
    Employee found = manager.getEmployee(1);
    assertThat(found.getName()).isEqualTo("Key Brown");

    // update
    e.setName("Key Updated");
    manager.updateEmployee(e);
    Employee updated = manager.getEmployee(1);
    assertThat(updated.getName()).isEqualTo("Key Updated");

    // count
    assertThat(manager.getEmployeeCount()).isEqualTo(1);

    // remove
    manager.removeEmployee(1);
    assertThat(manager.getEmployeeCount()).isZero();

    // assert exception when getting removed employee
    assertThatThrownBy(() -> manager.getEmployee(1))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Employee does not in collection with id");
  }
}
