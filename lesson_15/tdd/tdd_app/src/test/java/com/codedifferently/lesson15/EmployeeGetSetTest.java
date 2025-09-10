package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EmployeeGetSetTest {

  @Test
  void constructor_setsAllFields() {
    Employee e = new Employee(42, "Joy Brown", "Engineering", 123456.78);
    assertThat(e.getId()).isEqualTo(42);
    assertThat(e.getName()).isEqualTo("Joy Brown");
    assertThat(e.getDepartment()).isEqualTo("Engineering");
    assertThat(e.getSalary()).isEqualTo(123456.78);
  }

  @Test
  void setters_updateFields_and_getters_returnThem() {
    Employee e = new Employee(0, null, null, 0.0);

    e.setId(7);
    e.setName("Key Brown");
    e.setDepartment("Support");
    e.setSalary(52000.0);

    assertThat(e.getId()).isEqualTo(7);
    assertThat(e.getName()).isEqualTo("Key Brown");
    assertThat(e.getDepartment()).isEqualTo("Support");
    assertThat(e.getSalary()).isEqualTo(52000.0);
  }

  @Test
  void getDetails_handlesBlankDepartment() {
    Employee e = new Employee(8, "Pyes Brown", "   ", 61000.0);
    assertThat(e.getDetails())
      .isEqualTo("Employee[id=8, name=Pyes Brown, salary=61000.0]");
  }

  @Test
  void getDetails_handlesNullName() {
    Employee e = new Employee(9, null, "QA", 55000.0);
    assertThat(e.getDetails())
      .isEqualTo("Employee[id=9, name=, dept=QA, salary=55000.0]");
  }
}
