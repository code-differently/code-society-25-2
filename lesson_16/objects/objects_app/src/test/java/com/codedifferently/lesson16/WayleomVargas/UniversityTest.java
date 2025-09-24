package com.codedifferently.lesson16.WayleomVargas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UniversityTest {

  private University university;
  private Student student1;
  private Student student2;
  private Student student3;

  @BeforeEach
  void setUp() {
    university = new University("Tech State University", 100, UniversityType.STATE_UNIVERSITY);
    student1 = new Student("Alice Johnson", 1001, GradeLevel.FRESHMAN, 3.8);
    student2 = new Student("Bob Smith", 1002, GradeLevel.SOPHOMORE, 3.2);
    student3 = new Student("Carol Davis", 1003, GradeLevel.JUNIOR, 3.9);
  }

  @Test
  void testUniversityCreation() {
    assertEquals("Tech State University", university.getName());
    assertEquals(100, university.getMaxCapacity());
    assertEquals(UniversityType.STATE_UNIVERSITY, university.getUniversityType());
    assertTrue(university.isOpen());
    assertEquals(0.0, university.getAverageGPA());
  }

  @Test
  void testStudentEnrollment() throws UniversityFullException {
    university.enrollStudent(student1);
    assertEquals(3.8, university.getAverageGPA());

    university.enrollStudent(student2);
    assertEquals(3.5, university.getAverageGPA(), 0.01);

    List<Student> students = university.getStudents();
    assertEquals(2, students.size());
    assertTrue(students.contains(student1));
    assertTrue(students.contains(student2));
  }

  @Test
  void testUniversityCapacityException() {
    University smallUniversity = new University("Small University", 1, UniversityType.PRIVATE_UNIVERSITY);

    assertDoesNotThrow(
        () -> {
          smallUniversity.enrollStudent(student1);
        });

    UniversityFullException exception =
        assertThrows(
            UniversityFullException.class,
            () -> {
              smallUniversity.enrollStudent(student2);
            });

    assertTrue(exception.getMessage().contains("capacity"));
  }

  @Test
  void testStudentRemoval() throws UniversityFullException, StudentNotFoundException {
    university.enrollStudent(student1);
    university.enrollStudent(student2);

    university.removeStudent(1001);
    assertEquals(3.2, university.getAverageGPA());

    StudentNotFoundException exception =
        assertThrows(
            StudentNotFoundException.class,
            () -> {
              university.removeStudent(9999);
            });

    assertTrue(exception.getMessage().contains("not found"));
  }

  @Test
  void testHonorRollStudents() throws UniversityFullException {
    university.enrollStudent(student1);
    university.enrollStudent(student2);
    university.enrollStudent(student3);

    List<Student> honorRoll = university.getHonorRollStudents();
    assertEquals(2, honorRoll.size());
    assertTrue(honorRoll.contains(student1));
    assertTrue(honorRoll.contains(student3));
    assertFalse(honorRoll.contains(student2));
  }

  @Test
  void testGetStudentsByGrade() throws UniversityFullException {
    Student freshman1 = new Student("Dave Wilson", 1004, GradeLevel.FRESHMAN, 3.5);
    Student freshman2 = new Student("Eve Brown", 1005, GradeLevel.FRESHMAN, 3.7);

    university.enrollStudent(student1);
    university.enrollStudent(student2);
    university.enrollStudent(freshman1);
    university.enrollStudent(freshman2);

    List<Student> freshmen = university.getStudentsByGrade(GradeLevel.FRESHMAN);
    assertEquals(3, freshmen.size());

    List<Student> sophomores = university.getStudentsByGrade(GradeLevel.SOPHOMORE);
    assertEquals(1, sophomores.size());
    assertEquals(student2, sophomores.get(0));
  }

  @Test
  void testClosedUniversityEnrollment() {
    university.setOpen(false);

    UniversityFullException exception =
        assertThrows(
            UniversityFullException.class,
            () -> {
              university.enrollStudent(student1);
            });

    assertTrue(exception.getMessage().contains("closed"));
  }
}
