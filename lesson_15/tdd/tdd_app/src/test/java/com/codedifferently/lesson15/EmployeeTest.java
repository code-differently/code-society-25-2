package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EmployeeTest {

  @Test
  void getDetails_formatsAllFields() {
    Employee e = new Employee(123, "Joy Brown", "Engineering", 85000.0);

    String details = e.getDetails();

    assertThat(details)
      .isEqualTo("Employee[id=123, name=Joy Brown, dept=Engineering, salary=85000.0]");
  }

  @Test
  void getDetails_handlesNullDepartment() {
    Employee e = new Employee(7, "Pyes Brown", null, 60000.0);

    String details = e.getDetails();

    assertThat(details)
      .isEqualTo("Employee[id=7, name=Pyes Brown, salary=60000.0]");
  }

  @Test
  void getDetails_trimsNameAndDepartment() {
    Employee e = new Employee(9, "  Zach Brown ", "  QA  ", 55000.0);

    String details = e.getDetails();

    assertThat(details)
      .isEqualTo("Employee[id=9, name=Zach Brown, dept=QA, salary=55000.0]");
  }
}
