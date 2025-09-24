package com.codedifferently.lesson16.WayleomVargas;

import java.util.ArrayList;
import java.util.List;

public class University {
  private String name;
  private int maxCapacity;
  private boolean isOpen;
  private ArrayList<Student> students;
  private UniversityType universityType;
  private double averageGPA;

  public University(String name, int maxCapacity, UniversityType universityType) {
    this.name = name;
    this.maxCapacity = maxCapacity;
    this.universityType = universityType;
    this.isOpen = true;
    this.students = new ArrayList<>();
    this.averageGPA = 0.0;
  }

  public String getName() {
    return name;
  }

  public int getMaxCapacity() {
    return maxCapacity;
  }

  public boolean isOpen() {
    return isOpen;
  }

  public UniversityType getUniversityType() {
    return universityType;
  }

  public double getAverageGPA() {
    return averageGPA;
  }

  public List<Student> getStudents() {
    return new ArrayList<>(students);
  }

  public void setOpen(boolean open) {
    this.isOpen = open;
  }

  public void enrollStudent(Student student) throws UniversityFullException {
    if (!isOpen || students.size() >= maxCapacity) {
      throw new UniversityFullException("Cannot enroll: University closed or at capacity");
    }

    if (!students.contains(student)) {
      students.add(student);
      calculateAverageGPA();
    }
  }

  public void removeStudent(int studentId) throws StudentNotFoundException {
    Student toRemove = null;

    for (Student student : students) {
      if (student.getStudentId() == studentId) {
        toRemove = student;
        break;
      }
    }

    if (toRemove == null) {
      throw new StudentNotFoundException("Student ID " + studentId + " not found");
    }

    students.remove(toRemove);
    calculateAverageGPA();
  }

  private void calculateAverageGPA() {
    if (students.isEmpty()) {
      averageGPA = 0.0;
      return;
    }

    double totalGPA = 0.0;
    for (Student student : students) {
      totalGPA += student.getGpa();
    }
    averageGPA = totalGPA / students.size();
  }

  public List<Student> getHonorRollStudents() {
    List<Student> honorRoll = new ArrayList<>();
    for (Student student : students) {
      if (student.isHonorRoll()) {
        honorRoll.add(student);
      }
    }
    return honorRoll;
  }

  public List<Student> getStudentsByGrade(GradeLevel grade) {
    List<Student> result = new ArrayList<>();
    for (Student student : students) {
      if (student.getGradeLevel() == grade) {
        result.add(student);
      }
    }
    return result;
  }

  @Override
  public String toString() {
    return String.format(
        "University{name='%s', type=%s, students=%d/%d, avgGPA=%.2f}",
        name, universityType, students.size(), maxCapacity, averageGPA);
  }
}
