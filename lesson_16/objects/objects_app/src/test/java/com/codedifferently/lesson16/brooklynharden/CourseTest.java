package com.codedifferently.lesson16.brooklynharden;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CourseTest {
  private Course course;

  @BeforeEach
  public void setUp() {
    ArrayList<String> students =
        new ArrayList<>(
            Arrays.asList("Taylor", "Brooklyn", "Ketsia", "Meera", "Sammi", "Hamna", "Andy"));
    course =
        new Course(
            "Introduction to Computer Science",
            104,
            "Dr.Bart",
            3,
            students,
            true,
            CourseLevel.BEGINNER);
  }

  @Test
  public void getCourseNameTest() {
    String expected = "Introduction to Computer Science";
    assertEquals(course.getCourseName(), expected);
  }

  @Test
  public void getCourseNumberTest() {
    int expected = 104;
    assertEquals(course.getCourseNumber(), expected);
  }

  @Test
  public void testGetInstructor() {
    String expected = "Dr.Bart";
    assertEquals(course.getProfessor(), expected);
  }

  @Test
  public void testGetCourseCredits() {
    int expected = 3;
    assertEquals(course.getCredits(), expected);
  }

  @Test
  public void testStudentsEnrolled() {
    ArrayList<String> expected =
        new ArrayList<>(
            Arrays.asList("Taylor", "Brooklyn", "Ketsia", "Meera", "Sammi", "Hamna", "Andy"));

    assertEquals(course.getEnrolledStudents(), expected);
  }

  @Test
  public void testGetCourseActive() {
    boolean expected = true;
    assertEquals(course.isActive(), true);
  }

  @Test
  public void testGetCourseLevel() {
    CourseLevel expected = CourseLevel.BEGINNER;
    assertEquals(course.getCourseLevel(), expected);
  }

  @Test
  public void testEnrollStudentSucess() throws CourseException {
    course.enrollStudent("Olivia");
    assertEquals(true, course.isStudentEnrolled("Olivia"));
  }

  @Test
  public void testEnrollStudentCourseFull() {
    for (int i = 0; i <= 30; i++) {
      try {
        course.enrollStudent("Student" + i);
      } catch (CourseException e) {
      }
    }
    Exception exception =
        assertThrows(
            CourseException.class,
            () -> {
              course.enrollStudent("ExtraStudent");
            });
    assertEquals("Course is full", exception.getMessage());
  }

  @Test
  public void testRemoveStudent() {
    course.removeStudent("Brooklyn");
    assertEquals(false, course.isStudentEnrolled("Brooklyn"));
  }
}
