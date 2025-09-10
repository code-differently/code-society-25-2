package com.codedifferently.lesson15;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class Lesson15ExtraTest {
  @Test
  void main_runs_withoutThrowing() {
    assertThatNoException().isThrownBy(() -> Lesson15.main(null));
  }
}
