package com.codedifferently.lesson15;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class Lesson15Test {

  @Test
  public void testLesson15() {
    //Arrange + Act
    Lesson15 lesson15 = new Lesson15();
    //Assert
    assertThat(lesson15).isNotNull();
  }

  @Test
  public void testGetGreeting() {
    // Arrange
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    PrintStream originalOut = System.out;
    System.setOut(printStream);
    // Act
    Lesson15.main(null);
    //Restore
    System.setOut(originalOut);
    // Assert
    String expected = outputStream.toString().trim();
    assertThat(expected).isEqualTo("Hello, World!");

  }
}
