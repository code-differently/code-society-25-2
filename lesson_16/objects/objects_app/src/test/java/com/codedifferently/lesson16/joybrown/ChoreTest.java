package com.codedifferently.lesson16.joybrown;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class ChoreTest {

  @Test
  void constructor_setsFields() {
    LocalDate due = LocalDate.of(2025, 9, 15);
    Chore c = new Chore("Dishes", "Evening sink load", 20, due, Priority.MEDIUM);
    assertThat(c.getTitle()).isEqualTo("Dishes");
    assertThat(c.getPriority()).isEqualTo(Priority.MEDIUM);
    assertThat(c.isCompleted()).isFalse();
    assertThat(c.getDueDate()).isEqualTo(due);
  }

  @Test
  void addAssignee_addsUniqueNames() {
    Chore c = new Chore("Laundry", "Fold clothes", 30, LocalDate.of(2025, 9, 12), Priority.HIGH);
    boolean added1 = c.addAssignee("Pyes");
    boolean added2 = c.addAssignee("Zach");
    assertThat(added1).isTrue();
    assertThat(added2).isTrue();
    assertThat(c.getAssignees()).containsExactly("Pyes", "Zach");
  }

  @Test
  void addAssignee_rejectsBlank_throwsCustom() {
    Chore c = new Chore("Trash", "Take out trash", 5, LocalDate.of(2025, 9, 11), Priority.LOW);
    assertThrows(InvalidAssigneeException.class, () -> c.addAssignee("  "));
  }

  @Test
  void addAssignee_returnsFalseWhenDuplicateIgnoringCaseAndSpaces() {
    Chore c = new Chore("Homework", "Math", 60, LocalDate.of(2025, 9, 13), Priority.URGENT);
    assertThat(c.addAssignee("Zach")).isTrue();
    assertThat(c.addAssignee("  zach ")).isFalse();
    assertThat(c.getAssignees()).containsExactly("Zach");
  }

  @Test
  void isOverdue_trueWhenPastDueAndNotCompleted() {
    Chore c = new Chore("Vacuum", "Living room", 15, LocalDate.of(2025, 9, 1), Priority.MEDIUM);
    boolean overdue = c.isOverdue(LocalDate.of(2025, 9, 10));
    assertThat(overdue).isTrue();
  }

  @Test
  void isOverdue_falseWhenCompletedEvenIfPastDue() {
    Chore c = new Chore("Car wash", "Kia Optima", 40, LocalDate.of(2025, 9, 1), Priority.MEDIUM);
    c.markCompleted();
    boolean overdue = c.isOverdue(LocalDate.of(2025, 9, 10));
    assertThat(overdue).isFalse();
  }

  @Test
  void totalAssigneeNameLength_countsViaLoop() {
    Chore c = new Chore("Groceries", "Buy fruit", 25, LocalDate.of(2025, 9, 14), Priority.HIGH);
    c.addAssignee("Pyes");
    c.addAssignee("Zach");
    c.addAssignee("Key");
    int expected = "Pyes".length() + "Zach".length() + "Key".length();
    assertThat(c.totalAssigneeNameLength()).isEqualTo(expected);
  }
}
