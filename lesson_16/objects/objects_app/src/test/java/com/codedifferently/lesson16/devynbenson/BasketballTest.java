package com.codedifferently.lesson16.devynbenson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test class for Basketball */
public class BasketballTest {

  private Basketball basketball;

  @BeforeEach
  public void setUp() throws InvalidStatException {
    basketball = new Basketball("Spalding", 29.7, Position.CENTER);
  }

  @Test
  public void testConstructorValidInput() throws InvalidStatException {
    Basketball testBall = new Basketball("Nike", 29.5, Position.POINT_GUARD);
    assertEquals("Nike", testBall.getBrand());
    assertEquals(29.5, testBall.getCircumference());
    assertEquals(Position.POINT_GUARD, testBall.getPrimaryPosition());
    assertEquals(0, testBall.getGamesPlayed());
    assertTrue(testBall.isOfficialSize());
  }

  @Test
  public void testConstructorInvalidCircumference() {
    assertThrows(
        InvalidStatException.class,
        () -> {
          new Basketball("Wilson", -5.0, Position.SHOOTING_GUARD);
        });

    assertThrows(
        InvalidStatException.class,
        () -> {
          new Basketball("Wilson", 0.0, Position.SMALL_FORWARD);
        });
  }

  @Test
  public void testGetPerformanceRating() throws InvalidStatException {
    // Test low usage (0-20 games)
    assertEquals("Low Usage", basketball.getPerformanceRating());

    // Test medium usage (21-50 games)
    for (int i = 0; i < 25; i++) {
      basketball.incrementGamesPlayed();
    }
    assertEquals("Medium Usage", basketball.getPerformanceRating());

    // Test high usage (51+ games)
    for (int i = 0; i < 30; i++) {
      basketball.incrementGamesPlayed();
    }
    assertEquals("High Usage", basketball.getPerformanceRating());
  }

  @Test
  public void testAddPlayerUsage() throws InvalidStatException {
    // Test adding valid player names
    basketball.addPlayerUsage("LeBron James");
    basketball.addPlayerUsage("Stephen Curry");
    basketball.addPlayerUsage("Kevin Durant");

    assertEquals(3, basketball.getPlayerCount());
    assertTrue(basketball.hasBeenUsedByPlayer("LeBron James"));
    assertTrue(basketball.hasBeenUsedByPlayer("Stephen Curry"));
    assertTrue(basketball.hasBeenUsedByPlayer("Kevin Durant"));

    // Test adding duplicate player (should not increase count)
    basketball.addPlayerUsage("LeBron James");
    assertEquals(3, basketball.getPlayerCount());

    // Test adding invalid player names
    assertThrows(
        InvalidStatException.class,
        () -> {
          basketball.addPlayerUsage(null);
        });

    assertThrows(
        InvalidStatException.class,
        () -> {
          basketball.addPlayerUsage("");
        });

    assertThrows(
        InvalidStatException.class,
        () -> {
          basketball.addPlayerUsage("   ");
        });
  }

  @Test
  public void testGetPlayersReport() throws InvalidStatException {
    basketball.addPlayerUsage("Michael Jordan");
    basketball.addPlayerUsage("Kobe Bryant");
    basketball.addPlayerUsage("Shaquille O'Neal");

    String report = basketball.getPlayersReport();

    assertTrue(report.contains("Players who used this basketball:"));
    assertTrue(report.contains("1. Michael Jordan"));
    assertTrue(report.contains("2. Kobe Bryant"));
    assertTrue(report.contains("3. Shaquille O'Neal"));
  }

  @Test
  public void testIncrementGamesPlayed() throws InvalidStatException {
    assertEquals(0, basketball.getGamesPlayed());

    basketball.incrementGamesPlayed();
    assertEquals(1, basketball.getGamesPlayed());

    basketball.incrementGamesPlayed();
    basketball.incrementGamesPlayed();
    assertEquals(3, basketball.getGamesPlayed());
  }

  @Test
  public void testIncrementGamesPlayedMaxLimit() throws InvalidStatException {
    // Set games played close to the limit
    for (int i = 0; i < 999; i++) {
      basketball.incrementGamesPlayed();
    }
    assertEquals(999, basketball.getGamesPlayed());

    basketball.incrementGamesPlayed(); // This should reach the limit (1000)
    assertEquals(1000, basketball.getGamesPlayed());

    // This should throw an exception
    assertThrows(
        InvalidStatException.class,
        () -> {
          basketball.incrementGamesPlayed();
        });
  }

  @Test
  public void testSetCircumferenceValid() throws InvalidStatException {
    basketball.setCircumference(30.0);
    assertEquals(30.0, basketball.getCircumference());
    assertTrue(basketball.isOfficialSize());

    basketball.setCircumference(28.0);
    assertEquals(28.0, basketball.getCircumference());
    assertFalse(basketball.isOfficialSize());
  }

  @Test
  public void testSetCircumferenceInvalid() {
    assertThrows(
        InvalidStatException.class,
        () -> {
          basketball.setCircumference(-1.0);
        });

    assertThrows(
        InvalidStatException.class,
        () -> {
          basketball.setCircumference(0.0);
        });
  }

  @Test
  public void testOfficialSizeDetection() throws InvalidStatException {
    // Test official size range (29.5 - 30.0)
    Basketball officialBall1 = new Basketball("Spalding", 29.5, Position.CENTER);
    assertTrue(officialBall1.isOfficialSize());

    Basketball officialBall2 = new Basketball("Wilson", 30.0, Position.POWER_FORWARD);
    assertTrue(officialBall2.isOfficialSize());

    Basketball officialBall3 = new Basketball("Nike", 29.75, Position.SMALL_FORWARD);
    assertTrue(officialBall3.isOfficialSize());

    // Test non-official sizes
    Basketball smallBall = new Basketball("Generic", 28.0, Position.POINT_GUARD);
    assertFalse(smallBall.isOfficialSize());

    Basketball largeBall = new Basketball("Custom", 31.0, Position.SHOOTING_GUARD);
    assertFalse(largeBall.isOfficialSize());
  }
}
