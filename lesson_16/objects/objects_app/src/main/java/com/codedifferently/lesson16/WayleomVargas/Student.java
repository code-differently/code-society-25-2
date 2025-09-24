package com.codedifferently.lesson16.WayleomVargas;

public class Student {
  private String name;
  private int studentId;
  private GradeLevel gradeLevel;
  private double gpa;
  private boolean isEnrolled;

  public Student(String name, int studentId, GradeLevel gradeLevel, double gpa) {
    this.name = name;
    this.studentId = studentId;
    this.gradeLevel = gradeLevel;
    this.gpa = gpa;
    this.isEnrolled = true;
  }

  public String getName() {
    return name;
  }

  public int getStudentId() {
    return studentId;
  }

  public GradeLevel getGradeLevel() {
    return gradeLevel;
  }

  public double getGpa() {
    return gpa;
  }

  public boolean isEnrolled() {
    return isEnrolled;
  }

  public void setGpa(double gpa) {
    if (gpa >= 0.0 && gpa <= 4.0) {
      this.gpa = gpa;
    }
  }

  public void setEnrolled(boolean enrolled) {
    this.isEnrolled = enrolled;
  }

  public void setGradeLevel(GradeLevel gradeLevel) {
    this.gradeLevel = gradeLevel;
  }

  public boolean isHonorRoll() {
    return gpa >= 3.5;
  }

  @Override
  public String toString() {
    return String.format(
        "Student{name='%s', studentId=%d, gradeLevel=%s, gpa=%.2f, isEnrolled=%s}",
        name, studentId, gradeLevel, gpa, isEnrolled);
  }
}
