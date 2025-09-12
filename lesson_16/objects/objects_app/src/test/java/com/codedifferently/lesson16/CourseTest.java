package com.codedifferently.lesson16;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codedifferently.lesson16.brooklynharden.Course;
import com.codedifferently.lesson16.brooklynharden.CourseLevel;

public class CourseTest {
    private Course course;

    @BeforeEach
    public void setUp() {
        ArrayList<String> students = new ArrayList<>(Arrays.asList("Taylor", "Brooklyn", "Ketsia", "Meera", "Sammi", "Hamna", "Andy"));
        course = new Course("Introduction to Computer Science", 104, "Dr.Bart", 3, students,true, CourseLevel.BEGINNER);
  }

  @Test
  public void getCourseNameTest(){
    String expected = "Introduction to Computer Science";
    assertEquals(course.getCourseName(), expected);
  }
    
}
