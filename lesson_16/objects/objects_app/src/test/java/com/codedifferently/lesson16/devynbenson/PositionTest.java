package com.codedifferently.lesson16.devynbenson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Test class for Position enum */
public class PositionTest {

  @Test
  public void testPointGuardValues() {
    assertEquals("PG", Position.POINT_GUARD.getAbbreviation());
    assertEquals("Point Guard", Position.POINT_GUARD.getFullName());
  }

  @Test
  public void testShootingGuardValues() {
    assertEquals("SG", Position.SHOOTING_GUARD.getAbbreviation());
    assertEquals("Shooting Guard", Position.SHOOTING_GUARD.getFullName());
  }

  @Test
  public void testSmallForwardValues() {
    assertEquals("SF", Position.SMALL_FORWARD.getAbbreviation());
    assertEquals("Small Forward", Position.SMALL_FORWARD.getFullName());
  }

  @Test
  public void testPowerForwardValues() {
    assertEquals("PF", Position.POWER_FORWARD.getAbbreviation());
    assertEquals("Power Forward", Position.POWER_FORWARD.getFullName());
  }

  @Test
  public void testCenterValues() {
    assertEquals("C", Position.CENTER.getAbbreviation());
    assertEquals("Center", Position.CENTER.getFullName());
  }

  @Test
  public void testEnumValues() {
    Position[] positions = Position.values();
    assertEquals(5, positions.length);
    assertEquals(Position.POINT_GUARD, positions[0]);
    assertEquals(Position.SHOOTING_GUARD, positions[1]);
    assertEquals(Position.SMALL_FORWARD, positions[2]);
    assertEquals(Position.POWER_FORWARD, positions[3]);
    assertEquals(Position.CENTER, positions[4]);
  }

  @Test
  public void testValueOf() {
    assertEquals(Position.POINT_GUARD, Position.valueOf("POINT_GUARD"));
    assertEquals(Position.SHOOTING_GUARD, Position.valueOf("SHOOTING_GUARD"));
    assertEquals(Position.SMALL_FORWARD, Position.valueOf("SMALL_FORWARD"));
    assertEquals(Position.POWER_FORWARD, Position.valueOf("POWER_FORWARD"));
    assertEquals(Position.CENTER, Position.valueOf("CENTER"));
  }
}
