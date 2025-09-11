package com.codedifferently.lesson16.joybrown;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Chore {
  // ≥5 member variables; ≥3 types; includes a collection & enum
  private String title;
  private String description;
  private int estimatedMinutes;
  private LocalDate dueDate;
  private Priority priority;          // enum
  private List<String> assignees;     // collection
  private boolean completed;

  // constructor
  public Chore(String title, String description, int estimatedMinutes, LocalDate dueDate, Priority priority) {
    if (title == null || title.isBlank()) {
      throw new IllegalArgumentException("title must not be blank");
    }
    if (estimatedMinutes < 0) {
      throw new IllegalArgumentException("estimatedMinutes must be >= 0");
    }
    this.title = title;
    this.description = description;
    this.estimatedMinutes = estimatedMinutes;
    this.dueDate = dueDate;
    this.priority = Objects.requireNonNull(priority, "priority");
    this.assignees = new ArrayList<>();
    this.completed = false;
  }

  // uses the collection (+ loop) and throws your custom exception on bad input
  public boolean addAssignee(String name) {
    if (name == null || name.isBlank()) {
      throw new InvalidAssigneeException("Assignee name must not be blank");
    }
    String trimmed = name.trim();
    for (String a : assignees) {
      if (a.equalsIgnoreCase(trimmed)) {
        return false; // duplicate, not added
      }
    }
    assignees.add(trimmed);
    return true;
  }

  // method with a conditional expression
  public boolean isOverdue(LocalDate today) {
    LocalDate ref = (today != null) ? today : LocalDate.now();
    return !completed && dueDate != null && dueDate.isBefore(ref);
  }

  // method that explicitly loops over the collection
  public int totalAssigneeNameLength() {
    int sum = 0;
    for (String a : assignees) {
      sum += a.length();
    }
    return sum;
  }

  public void markCompleted() {
    this.completed = true;
  }

  // getters (used by tests)
  public String getTitle() { return title; }
  public String getDescription() { return description; }
  public int getEstimatedMinutes() { return estimatedMinutes; }
  public LocalDate getDueDate() { return dueDate; }
  public Priority getPriority() { return priority; }
  public boolean isCompleted() { return completed; }
  public List<String> getAssignees() { return new ArrayList<>(assignees); }
}
