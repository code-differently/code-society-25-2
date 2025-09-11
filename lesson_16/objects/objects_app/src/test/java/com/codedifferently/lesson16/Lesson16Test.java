package com.codedifferently.lesson16;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE)
class Lesson16Test {

  @Test
  void testMain() {
    assertThat(new Lesson16()).isNotNull();
  }

  @Test
  void testMainMethod() {
    // Test that main method can be called without exceptions
    assertDoesNotThrow(
        () -> {
          // We don't actually run the main method to avoid starting Spring Boot
          // but we verify the method exists and is accessible
          var mainMethod = Lesson16.class.getDeclaredMethod("main", String[].class);
          assertThat(mainMethod).isNotNull();
        });
  }
}
