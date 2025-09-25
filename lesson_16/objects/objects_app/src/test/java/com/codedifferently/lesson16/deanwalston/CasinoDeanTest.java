package com.codedifferently.lesson16.deanwalston;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CasinoDeanTest extends Object {

  private CasinoDean casino;

  @BeforeEach
  public void setUp() {
    String[] players = {"Player1", "Player2", "Player3", "Player4", "Player5"};

    casino =
        new CasinoDean(
            1, // playerId
            players, // players
            1000000.0, // casinoRevenue
            true, // isOpen
            CasinoDean.GameTypes.POKER, // currentGameType
            2500000L // annualVisitors
            );
  }

  @Test
  public void testSingleArgumentConstructor() {
    CasinoDean casinoWithGameType = new CasinoDean(CasinoDean.GameTypes.BLACKJACK);
    assertEquals(CasinoDean.GameTypes.BLACKJACK, casinoWithGameType.getCurrentGameType());
  }

  @Test
  public void testPlaceBet_ValidBet() {
    try {
      boolean result = casino.placeBet(1, 100.0);
      assertTrue(result);
      assertEquals(100.0, casino.getPlayerBet());
      // Check that the casino takes 5% of the bet
      assertEquals(1000000.0 + (100.0 * 0.05), casino.getCasinoRevenue());
    } catch (CasinoDean.CasinoException e) {
      fail("Should not throw exception for valid bet: " + e.getMessage());
    }
  }

  @Test
  public void testPlaceBet_CasinoClosed() {
    casino.setOpen(false);

    Exception exception =
        assertThrows(
            CasinoDean.CasinoException.class,
            () -> {
              casino.placeBet(1, 100.0);
            });
    assertTrue(exception.getMessage().contains("Casino is closed"));
  }

  @Test
  public void testPlaceBet_NullPlayers() {
    casino.setPlayers(null);
    Exception exception =
        assertThrows(
            CasinoDean.CasinoException.class,
            () -> {
              casino.placeBet(1, 100.0);
            });
    assertTrue(exception.getMessage().contains("No player data available"));
  }

  @Test
  public void testPlaceBet_EmptyPlayers() {
    casino.setPlayers(new String[0]);

    Exception exception =
        assertThrows(
            CasinoDean.CasinoException.class,
            () -> {
              casino.placeBet(1, 100.0);
            });
    assertTrue(exception.getMessage().contains("Player list is empty"));
  }

  @Test
  public void testPlaceBet_InvalidPlayerId() {
    Exception exception =
        assertThrows(
            CasinoDean.CasinoException.class,
            () -> {
              casino.placeBet(10, 100.0); // Invalid player ID
            });
    assertTrue(exception.getMessage().contains("Invalid player ID"));
  }

  @Test
  public void testPlaceBet_InvalidBetAmount() {
    Exception exception =
        assertThrows(
            CasinoDean.CasinoException.class,
            () -> {
              casino.placeBet(1, -50.0); // Invalid bet amount
            });
    assertTrue(exception.getMessage().contains("Invalid bet amount"));
  }

  @Test
  public void testPlaceBet_ExcessiveBetAmount() {
    Exception exception =
        assertThrows(
            CasinoDean.CasinoException.class,
            () -> {
              casino.placeBet(1, 15000.0); // Excessive bet amount
            });
    assertTrue(exception.getMessage().contains("Invalid bet amount"));
  }

  @Test
  public void testGetAvailableGames() {
    try {
      CasinoDean.GameTypes[] availableGames = casino.getAvailableGames(50);
      assertNotNull(availableGames);
      assertTrue(availableGames.length > 0);
    } catch (CasinoDean.CasinoException e) {
      fail("Should not throw exception: " + e.getMessage());
    }
  }

  @Test
  public void testGetAvailableGames_InvalidThreshold() {
    Exception exception =
        assertThrows(
            CasinoDean.CasinoException.class,
            () -> {
              casino.getAvailableGames(150); // Invalid threshold
            });
    assertTrue(exception.getMessage().contains("Popularity threshold must be between 0 and 100"));
  }

  @Test
  public void testGetAvailableGames_NegativeThreshold() {
    Exception exception =
        assertThrows(
            CasinoDean.CasinoException.class,
            () -> {
              casino.getAvailableGames(-10); // Negative threshold
            });
    assertTrue(exception.getMessage().contains("Popularity threshold must be between 0 and 100"));
  }

  @Test
  public void testCalculateTotalRevenue() {
    try {
      double totalRevenue = casino.calculateTotalRevenue();
      assertTrue(totalRevenue > 0);
    } catch (CasinoDean.CasinoException e) {
      fail("Should not throw exception: " + e.getMessage());
    }
  }

  @Test
  public void testCalculateTotalRevenue_NullPlayers() {
    casino.setPlayers(null);

    Exception exception =
        assertThrows(
            CasinoDean.CasinoException.class,
            () -> {
              casino.calculateTotalRevenue();
            });
    assertTrue(exception.getMessage().contains("Players data is not initialized"));
  }

  @Test
  public void testCalculateTotalRevenue_EmptyPlayers() {
    casino.setPlayers(new String[0]);

    Exception exception =
        assertThrows(
            CasinoDean.CasinoException.class,
            () -> {
              casino.calculateTotalRevenue();
            });
    assertTrue(exception.getMessage().contains("No players available"));
  }

  @Test
  public void testGettersAndSetters() {
    // Test getters and setters
    assertEquals("Casa Dean Casino", casino.getCasinoName());
    assertTrue(casino.isOpen());

    casino.setOpen(false);
    assertFalse(casino.isOpen());

    assertEquals(2500000L, casino.getAnnualVisitors());
    casino.setAnnualVisitors(3000000L);
    assertEquals(3000000L, casino.getAnnualVisitors());

    assertEquals(25.5f, casino.getAverageBet(), 0.001);

    assertEquals(1, casino.getPlayerId());
    casino.setPlayerId(2);
    assertEquals(2, casino.getPlayerId());

    casino.setCasinoRevenue(2000000.0);
    assertEquals(2000000.0, casino.getCasinoRevenue(), 0.001);

    assertEquals(CasinoDean.GameTypes.POKER, casino.getCurrentGameType());
    casino.setCurrentGameType(CasinoDean.GameTypes.BLACKJACK);
    assertEquals(CasinoDean.GameTypes.BLACKJACK, casino.getCurrentGameType());

    casino.setPlayerBet(200.0);
    assertEquals(200.0, casino.getPlayerBet(), 0.001);

    String[] newPlayers = {"Player6", "Player7"};
    casino.setPlayers(newPlayers);
    assertArrayEquals(newPlayers, casino.getPlayers());
  }

  @Test
  public void testCasinoException() {
    CasinoDean.CasinoException exception1 =
        new CasinoDean(CasinoDean.GameTypes.POKER).new CasinoException("Test exception");
    assertEquals("Test exception", exception1.getMessage());

    Exception cause = new RuntimeException("Cause");
    CasinoDean.CasinoException exception2 =
        new CasinoDean(CasinoDean.GameTypes.POKER).new CasinoException("Test with cause", cause);
    assertEquals("Test with cause", exception2.getMessage());
    assertEquals(cause, exception2.getCause());
  }
}
