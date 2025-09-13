package com.codedifferently.lesson16.devynbenson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** Additional edge case tests for Basketball */
public class BasketballEdgeCaseTest {

  @Test
  public void testConstructorWithExactBoundaryValues() throws InvalidStatException {
    // Test exact official size boundaries
    Basketball minOfficial = new Basketball("Min Official", 29.5, Position.POINT_GUARD);
    assertTrue(minOfficial.isOfficialSize());

    Basketball maxOfficial = new Basketball("Max Official", 30.0, Position.CENTER);
    assertTrue(maxOfficial.isOfficialSize());

    // Test just outside boundaries
    Basketball justUnder = new Basketball("Just Under", 29.49, Position.SHOOTING_GUARD);
    assertFalse(justUnder.isOfficialSize());

    Basketball justOver = new Basketball("Just Over", 30.01, Position.POWER_FORWARD);
    assertFalse(justOver.isOfficialSize());
  }

  @Test
  public void testConstructorWithVerySmallPositiveCircumference() throws InvalidStatException {
    Basketball tiny = new Basketball("Tiny Ball", 0.1, Position.SMALL_FORWARD);
    assertEquals(0.1, tiny.getCircumference());
    assertFalse(tiny.isOfficialSize());
  }

  @Test
  public void testConstructorWithLargeCircumference() throws InvalidStatException {
    Basketball giant = new Basketball("Giant Ball", 100.0, Position.CENTER);
    assertEquals(100.0, giant.getCircumference());
    assertFalse(giant.isOfficialSize());
  }

  @Test
  public void testSetCircumferenceUpdateOfficialSizeStatus() throws InvalidStatException {
    Basketball ball = new Basketball("Test", 25.0, Position.POINT_GUARD);
    assertFalse(ball.isOfficialSize());

    // Change to official size
    ball.setCircumference(29.7);
    assertTrue(ball.isOfficialSize());

    // Change back to non-official
    ball.setCircumference(35.0);
    assertFalse(ball.isOfficialSize());
  }

  @Test
  public void testAddPlayerUsageWithWhitespaceVariations() throws InvalidStatException {
    Basketball ball = new Basketball("Test", 29.7, Position.CENTER);

    // Test various whitespace scenarios
    assertThrows(InvalidStatException.class, () -> ball.addPlayerUsage(""));
    assertThrows(InvalidStatException.class, () -> ball.addPlayerUsage(" "));
    assertThrows(InvalidStatException.class, () -> ball.addPlayerUsage("\t"));
    assertThrows(InvalidStatException.class, () -> ball.addPlayerUsage("\n"));
    assertThrows(InvalidStatException.class, () -> ball.addPlayerUsage("   \t\n   "));
  }

  @Test
  public void testAddPlayerUsageWithValidWhitespace() throws InvalidStatException {
    Basketball ball = new Basketball("Test", 29.7, Position.CENTER);

    // These should work (names with internal whitespace)
    ball.addPlayerUsage("LeBron James");
    ball.addPlayerUsage("Michael Jordan Jr.");
    ball.addPlayerUsage("Karl-Anthony Towns");

    assertEquals(3, ball.getPlayerCount());
  }

  @Test
  public void testPerformanceRatingBoundaryValues() throws InvalidStatException {
    Basketball ball = new Basketball("Test", 29.7, Position.CENTER);

    // Test exact boundaries
    assertEquals("Low Usage", ball.getPerformanceRating()); // 0 games

    for (int i = 0; i < 20; i++) {
      ball.incrementGamesPlayed();
    }
    assertEquals("Low Usage", ball.getPerformanceRating()); // 20 games

    ball.incrementGamesPlayed();
    assertEquals("Medium Usage", ball.getPerformanceRating()); // 21 games

    for (int i = 0; i < 29; i++) {
      ball.incrementGamesPlayed();
    }
    assertEquals("Medium Usage", ball.getPerformanceRating()); // 50 games

    ball.incrementGamesPlayed();
    assertEquals("High Usage", ball.getPerformanceRating()); // 51 games
  }

  @Test
  public void testGameLimitBoundaryConditions() throws InvalidStatException {
    Basketball ball = new Basketball("Test", 29.7, Position.CENTER);

    // Set to just before limit
    for (int i = 0; i < 999; i++) {
      ball.incrementGamesPlayed();
    }
    assertEquals(999, ball.getGamesPlayed());

    // Reach exact limit
    ball.incrementGamesPlayed();
    assertEquals(1000, ball.getGamesPlayed());

    // Verify we can't go beyond
    assertThrows(InvalidStatException.class, () -> ball.incrementGamesPlayed());
    assertEquals(1000, ball.getGamesPlayed()); // Should remain at limit
  }

  @Test
  public void testToStringWithDifferentStates() throws InvalidStatException {
    Basketball ball = new Basketball("Nike Premium", 29.8, Position.SMALL_FORWARD);

    // Test toString with no players
    String emptyState = ball.toString();
    assertTrue(emptyState.contains("Nike Premium"));
    assertTrue(emptyState.contains("29.8"));
    assertTrue(emptyState.contains("Small Forward"));
    assertTrue(emptyState.contains("playersUsed=0"));

    // Test toString with players and games
    ball.addPlayerUsage("Kevin Durant");
    ball.addPlayerUsage("Giannis Antetokounmpo");
    for (int i = 0; i < 75; i++) {
      ball.incrementGamesPlayed();
    }

    String fullState = ball.toString();
    assertTrue(fullState.contains("Nike Premium"));
    assertTrue(fullState.contains("gamesPlayed=75"));
    assertTrue(fullState.contains("playersUsed=2"));
  }

  @Test
  public void testAllPositionEnumValues() throws InvalidStatException {
    // Test creating basketballs with each position
    Basketball pg = new Basketball("Test", 29.7, Position.POINT_GUARD);
    assertEquals("Point Guard", pg.getPrimaryPosition().getFullName());
    assertEquals("PG", pg.getPrimaryPosition().getAbbreviation());

    Basketball sg = new Basketball("Test", 29.7, Position.SHOOTING_GUARD);
    assertEquals("Shooting Guard", sg.getPrimaryPosition().getFullName());
    assertEquals("SG", sg.getPrimaryPosition().getAbbreviation());

    Basketball sf = new Basketball("Test", 29.7, Position.SMALL_FORWARD);
    assertEquals("Small Forward", sf.getPrimaryPosition().getFullName());
    assertEquals("SF", sf.getPrimaryPosition().getAbbreviation());

    Basketball pf = new Basketball("Test", 29.7, Position.POWER_FORWARD);
    assertEquals("Power Forward", pf.getPrimaryPosition().getFullName());
    assertEquals("PF", pf.getPrimaryPosition().getAbbreviation());

    Basketball c = new Basketball("Test", 29.7, Position.CENTER);
    assertEquals("Center", c.getPrimaryPosition().getFullName());
    assertEquals("C", c.getPrimaryPosition().getAbbreviation());
  }

  @Test
  public void testPlayerReportFormattingWithManyPlayers() throws InvalidStatException {
    Basketball ball = new Basketball("Test", 29.7, Position.CENTER);

    // Add many players to test formatting
    String[] players = {
      "Player 1", "Player 2", "Player 3", "Player 4", "Player 5",
      "Player 6", "Player 7", "Player 8", "Player 9", "Player 10"
    };

    for (String player : players) {
      ball.addPlayerUsage(player);
    }

    String report = ball.getPlayersReport();

    // Verify formatting
    assertTrue(report.contains("Players who used this basketball:"));
    for (int i = 0; i < players.length; i++) {
      assertTrue(report.contains((i + 1) + ". " + players[i]));
    }
  }
}
