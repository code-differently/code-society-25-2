package com.codedifferently.lesson16.deanwalston;

public class CasinoDean {
  private final String casinoName = "Casa Dean Casino";
  private boolean isOpen = true;
  private long annualVisitors = 2_500_000L;
  private final float averageBet = 25.5f;
  private int playerId;
  private double casinoRevenue;
  private GameTypes currentGameType;
  private double playerBet;
  private String[] players;

  public enum GameTypes {
    POKER,
    BLACKJACK,
    ROULETTE,
    SLOTS,
    BACCARAT,
    SPADES,
    UNO
  }

  public CasinoDean(GameTypes gameType) {
    this.currentGameType = gameType;
  }

  public CasinoDean(
      int playerId,
      String[] players,
      double casinoRevenue,
      boolean isOpen,
      GameTypes gameType,
      long annualVisitors) {
    this.playerId = playerId;
    this.players = players;
    this.currentGameType = gameType;
    this.playerBet = 0.0; // Default 0 bet
    this.casinoRevenue = casinoRevenue;
    this.isOpen = isOpen;
    this.annualVisitors = annualVisitors;
  }

  public String getCasinoName() {
    return casinoName;
  }

  public boolean isOpen() {
    return isOpen;
  }

  public void setOpen(boolean open) {
    isOpen = open;
  }

  public long getAnnualVisitors() {
    return annualVisitors;
  }

  public void setAnnualVisitors(long annualVisitors) {
    this.annualVisitors = annualVisitors;
  }

  public float getAverageBet() {
    return averageBet;
  }

  public int getPlayerId() {
    return playerId;
  }

  public void setPlayerId(int playerId) {
    this.playerId = playerId;
  }

  public double getCasinoRevenue() {
    return casinoRevenue;
  }

  public void setCasinoRevenue(double casinoRevenue) {
    this.casinoRevenue = casinoRevenue;
  }

  public GameTypes getCurrentGameType() {
    return currentGameType;
  }

  public void setCurrentGameType(GameTypes currentGameType) {
    this.currentGameType = currentGameType;
  }

  public double getPlayerBet() {
    return playerBet;
  }

  public void setPlayerBet(double playerBet) {
    this.playerBet = playerBet;
  }

  public String[] getPlayers() {
    return players;
  }

  public void setPlayers(String[] players) {
    this.players = players;
  }

  /**
   * Places a bet for a player
   *
   * @param playerId The ID of the player
   * @param amount The amount to bet
   * @return Whether the bet was successful
   * @throws CasinoException If the casino is closed, player ID is invalid, or bet amount is invalid
   */
  public boolean placeBet(int playerId, double amount) throws CasinoException {
    if (!isOpen) {
      throw new CasinoException("Casino is closed. Cannot place bets.");
    }
    if (players == null) {
      throw new CasinoException("No player data available for processing bets");
    }
    if (players.length == 0) {
      throw new CasinoException("Player list is empty. Cannot place bets.");
    }
    if (playerId < 0 || playerId >= players.length) {
      throw new CasinoException(
          "Invalid player ID: " + playerId + ". Must be between 0 and " + (players.length - 1));
    }
    if (amount <= 0) {
      throw new CasinoException("Invalid bet amount: must be greater than $0.00");
    }
    if (amount > 10000) {
      throw new CasinoException("Invalid bet amount: exceeds maximum allowed ($10,000)");
    }
    this.playerId = playerId;
    this.playerBet = amount;
    // Calculate house take (5%)
    double houseTake = amount * 0.05;
    this.casinoRevenue += houseTake;
    return true;
  }

  /**
   * Finds popular games from a list based on a minimum popularity threshold
   *
   * @param minPopularity Minimum popularity threshold (0-100)
   * @return Array of available game types
   * @throws CasinoException If popularity threshold is invalid or if game type is null
   */
  public GameTypes[] getAvailableGames(int minPopularity) throws CasinoException {
    if (minPopularity < 0 || minPopularity > 100) {
      throw new CasinoException("Popularity threshold must be between 0 and 100");
    }
    GameTypes[] allGames = GameTypes.values();
    if (allGames.length == 0) {
      throw new CasinoException("No game types available");
    }
    // First count the games that meet the popularity threshold
    int count = 0;
    try {
      for (GameTypes game : allGames) {
        if (getGamePopularity(game) >= minPopularity) {
          count++;
        }
      }
      if (count == 0) {
        throw new CasinoException(
            "No games meet the minimum popularity threshold of " + minPopularity);
      }
      // Create and populate the result array
      GameTypes[] availableGames = new GameTypes[count];
      int index = 0;
      for (GameTypes game : allGames) {
        if (getGamePopularity(game) >= minPopularity) {
          availableGames[index++] = game;
        }
      }
      return availableGames;
    } catch (CasinoException e) {
      throw new CasinoException("Error calculating game popularity: " + e.getMessage());
    }
  }

  /**
   * Calculate total revenue from all players
   *
   * @return Total revenue
   * @throws CasinoException If player data is not available
   */
  public double calculateTotalRevenue() throws CasinoException {
    if (players == null) {
      throw new CasinoException("Players data is not initialized");
    }
    if (players.length == 0) {
      throw new CasinoException("No players available to calculate revenue");
    }
    double baseRevenue = casinoRevenue;
    double playerRevenue = calculatePlayerBasedRevenue();
    return baseRevenue + playerRevenue;
  }

  /**
   * Helper method to calculate revenue from player activity
   *
   * @return The calculated player-based revenue
   */
  private double calculatePlayerBasedRevenue() {
    double totalPlayerRevenue = 0;
    for (int i = 0; i < players.length; i++) {
      // Realistic revenue model based on player index
      // Higher index players contribute more revenue
      double baseContribution = 100.0;
      double variableContribution = i * 50.0;
      if (players[i] != null) {
        double playerContribution = baseContribution + variableContribution;
        totalPlayerRevenue += playerContribution;
      }
    }
    return totalPlayerRevenue;
  }

  /**
   * Helper method to calculate game popularity (for demonstration)
   *
   * @param gameType The type of game
   * @return Popularity score (0-100)
   * @throws CasinoException If gameType is null
   */
  private int getGamePopularity(GameTypes gameType) throws CasinoException {
    if (gameType == null) {
      throw new CasinoException("Cannot calculate popularity for null game type");
    }
    // assign popularity based on ordinal and name length
    return ((gameType.ordinal() * 15) + (gameType.name().length() * 5)) % 100;
  }

  /** Custom checked exception for casino operations that requires explicit handling */
  public class CasinoException extends Exception {
    /**
     * @param Message
     */
    public CasinoException(String message) {
      super(message);
    }

    /**
     * Constructs a new CasinoException with the specified detail message and cause
     *
     * @param message The detailed error message
     * @param cause The cause of this exception
     */
    public CasinoException(String message, Throwable cause) {
      super(message, cause);
    }
  }
}
