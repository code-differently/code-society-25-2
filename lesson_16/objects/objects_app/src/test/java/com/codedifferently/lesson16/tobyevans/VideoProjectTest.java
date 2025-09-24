package com.codedifferently.lesson16.tobyevans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class VideoProjectTest {
  @Test
  void addClip_increasesCountAndDuration() {
    VideoProject v = new VideoProject("Toby", true);
    v.addClip("Intro", 10);
    v.addClip("Main", 20);
    assertEquals(2, v.getClipCount());
    assertEquals(30, v.getTotalSeconds());
  }

  @Test
  void trim_reducesDuration() {
    VideoProject v = new VideoProject("Toby", false);
    v.addClip("A", 50);
    v.trim(10);
    assertEquals(40, v.getTotalSeconds());
  }

  @Test
  void trim_tooMuch_throws() {
    VideoProject v = new VideoProject("Toby", false);
    v.addClip("A", 30);
    assertThrows(InvalidOrderOperationException.class, () -> v.trim(40));
  }

  @Test
  void publish_changesStatus() {
    VideoProject v = new VideoProject("Toby", true);
    assertEquals(Status.PENDING, v.getStatus());
    v.publish();
    assertEquals(Status.PAID, v.getStatus());
  }

  @Test
  void getSummary_containsClipsAndStatus() {
    VideoProject v = new VideoProject("Toby", true);
    v.addClip("X", 5);
    v.addClip("Y", 7);
    String s = v.getSummary();
    assertTrue(s.contains("X"));
    assertTrue(s.contains("Y"));
    assertTrue(s.toLowerCase().contains("status"));
  }

  @Test
  void cancel_afterPublish_throws() {
    VideoProject v = new VideoProject("Toby", false);
    v.addClip("A", 10);
    v.publish();
    assertThrows(InvalidOrderOperationException.class, v::cancel);
  }
}
