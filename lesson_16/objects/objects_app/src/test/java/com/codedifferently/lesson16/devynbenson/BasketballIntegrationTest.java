package com.codedifferently.lesson16.devynbenson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** Integration tests for Basketball system */
public class BasketballIntegrationTest {

  @Test
  public void testCompleteGameScenario() throws InvalidStatException {
    // Create basketballs for different positions
    Basketball centerBall = new Basketball("Spalding NBA", 29.7, Position.CENTER);
    Basketball guardBall = new Basketball("Wilson Evolution", 29.5, Position.POINT_GUARD);

    // Simulate a basketball game scenario
    // Centers using the center ball
    centerBall.addPlayerUsage("Shaquille O'Neal");
    centerBall.addPlayerUsage("Dwight Howard");
    centerBall.addPlayerUsage("Anthony Davis");

    // Guards using the guard ball
    guardBall.addPlayerUsage("Stephen Curry");
    guardBall.addPlayerUsage("Chris Paul");
    guardBall.addPlayerUsage("Russell Westbrook");

    // Simulate games played
    for (int i = 0; i < 30; i++) {
      centerBall.incrementGamesPlayed();
      guardBall.incrementGamesPlayed();
    }

    // Verify game simulation results
    assertEquals("Medium Usage", centerBall.getPerformanceRating());
    assertEquals("Medium Usage", guardBall.getPerformanceRating());
    assertEquals(3, centerBall.getPlayerCount());
    assertEquals(3, guardBall.getPlayerCount());

    // Verify position-specific usage
    assertEquals(Position.CENTER, centerBall.getPrimaryPosition());
    assertEquals(Position.POINT_GUARD, guardBall.getPrimaryPosition());

    // Verify both balls are official size
    assertTrue(centerBall.isOfficialSize());
    assertTrue(guardBall.isOfficialSize());
  }

  @Test
  public void testSeasonLongUsageScenario() throws InvalidStatException {
    Basketball seasonBall = new Basketball("Official NBA Game Ball", 29.7, Position.SMALL_FORWARD);

    // Add multiple players throughout the season
    String[] players = {
      "LeBron James", "Kevin Durant", "Giannis Antetokounmpo",
      "Kawhi Leonard", "Paul George", "Jimmy Butler",
      "Jayson Tatum", "Luka Doncic", "Zion Williamson"
    };

    for (String player : players) {
      seasonBall.addPlayerUsage(player);
    }

    // Simulate season games (82 games)
    for (int i = 0; i < 82; i++) {
      seasonBall.incrementGamesPlayed();
    }

    // Verify season-end statistics
    assertEquals("High Usage", seasonBall.getPerformanceRating());
    assertEquals(9, seasonBall.getPlayerCount());
    assertEquals(82, seasonBall.getGamesPlayed());

    // Verify player report contains all players
    String report = seasonBall.getPlayersReport();
    for (String player : players) {
      assertTrue(report.contains(player));
    }
  }

  @Test
  public void testExceptionHandlingInComplexScenario() throws InvalidStatException {
    Basketball testBall = new Basketball("Test Ball", 29.6, Position.POWER_FORWARD);

    // Add players to approach game limit
    for (int i = 0; i < 999; i++) {
      testBall.incrementGamesPlayed();
    }

    // This should work (reaches exactly 1000)
    testBall.incrementGamesPlayed();
    assertEquals(1000, testBall.getGamesPlayed());

    // This should throw exception (exceeds limit)
    assertThrows(
        InvalidStatException.class,
        () -> {
          testBall.incrementGamesPlayed();
        });

    // Test invalid player scenarios
    assertThrows(
        InvalidStatException.class,
        () -> {
          testBall.addPlayerUsage(null);
        });

    assertThrows(
        InvalidStatException.class,
        () -> {
          testBall.addPlayerUsage("");
        });

    assertThrows(
        InvalidStatException.class,
        () -> {
          testBall.addPlayerUsage("   ");
        });
  }

  @Test
  public void testAllPositionsAndSizeVariations() throws InvalidStatException {
    Position[] positions = Position.values();
    double[] circumferences = {28.0, 29.5, 29.7, 30.0, 31.0};

    for (Position position : positions) {
      for (double circumference : circumferences) {
        Basketball ball = new Basketball("Multi-Test Ball", circumference, position);

        // Test basic functionality for each combination
        ball.addPlayerUsage("Test Player " + position.getAbbreviation());
        ball.incrementGamesPlayed();

        assertEquals(1, ball.getPlayerCount());
        assertEquals(1, ball.getGamesPlayed());
        assertEquals("Low Usage", ball.getPerformanceRating());
        assertEquals(position, ball.getPrimaryPosition());

        // Check official size detection
        boolean expectedOfficialSize = (circumference >= 29.5 && circumference <= 30.0);
        assertEquals(expectedOfficialSize, ball.isOfficialSize());
      }
    }
  }

  @Test
  public void testBasketballDemoScenario() throws InvalidStatException {
    // Replicate the demo scenario for testing
    Basketball spalding = new Basketball("Spalding Official NBA", 29.7, Position.CENTER);
    Basketball wilson = new Basketball("Wilson Evolution", 29.5, Position.POINT_GUARD);

    // Add NBA stars
    spalding.addPlayerUsage("Shaquille O'Neal");
    spalding.addPlayerUsage("Dwight Howard");
    spalding.addPlayerUsage("Anthony Davis");
    spalding.addPlayerUsage("Joel Embiid");

    wilson.addPlayerUsage("Stephen Curry");
    wilson.addPlayerUsage("Chris Paul");
    wilson.addPlayerUsage("Russell Westbrook");

    // Simulate different usage patterns
    for (int i = 0; i < 25; i++) {
      spalding.incrementGamesPlayed();
    }
    for (int i = 0; i < 60; i++) {
      wilson.incrementGamesPlayed();
    }

    // Verify expected results
    assertEquals("Medium Usage", spalding.getPerformanceRating());
    assertEquals("High Usage", wilson.getPerformanceRating());
    assertEquals(4, spalding.getPlayerCount());
    assertEquals(3, wilson.getPlayerCount());

    // Verify reports contain expected players
    String spaldingReport = spalding.getPlayersReport();
    assertTrue(spaldingReport.contains("Shaquille O'Neal"));
    assertTrue(spaldingReport.contains("Joel Embiid"));

    String wilsonReport = wilson.getPlayersReport();
    assertTrue(wilsonReport.contains("Stephen Curry"));
    assertTrue(wilsonReport.contains("Chris Paul"));
  }
}
