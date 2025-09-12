package com.codedifferently.lesson16.devynbenson;

import java.util.ArrayList;
import java.util.List;

/** Represents an NBA Basketball with player statistics and game data */
public class Basketball {
  private String brand;
  private double circumference;
  private int gamesPlayed;
  private boolean isOfficialSize;
  private List<String> playersUsed;
  private Position primaryPosition;

  public Basketball(String brand, double circumference, Position primaryPosition)
      throws InvalidStatException {
    if (circumference <= 0) {
      throw new InvalidStatException("Circumference must be positive");
    }
    this.brand = brand;
    this.circumference = circumference;
    this.primaryPosition = primaryPosition;
    this.gamesPlayed = 0;
    this.isOfficialSize = (circumference >= 29.5 && circumference <= 30.0);
    this.playersUsed = new ArrayList<>();
  }

  public String getPerformanceRating() {
    return gamesPlayed > 50 ? "High Usage" : gamesPlayed > 20 ? "Medium Usage" : "Low Usage";
  }

  public void addPlayerUsage(String playerName) throws InvalidStatException {
    if (playerName == null || playerName.trim().isEmpty()) {
      throw new InvalidStatException("Player name cannot be null or empty");
    }
    if (!playersUsed.contains(playerName)) {
      playersUsed.add(playerName);
    }
  }

  public String getPlayersReport() {
    StringBuilder report = new StringBuilder("Players who used this basketball:\n");
    for (int i = 0; i < playersUsed.size(); i++) {
      report.append((i + 1)).append(". ").append(playersUsed.get(i)).append("\n");
    }
    return report.toString();
  }

  public void incrementGamesPlayed() throws InvalidStatException {
    if (gamesPlayed >= 1000) {
      throw new InvalidStatException("Basketball has reached maximum game limit");
    }
    gamesPlayed++;
  }

  public int getPlayerCount() {
    return playersUsed.size();
  }

  public boolean hasBeenUsedByPlayer(String playerName) {
    return playersUsed.contains(playerName);
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public double getCircumference() {
    return circumference;
  }

  public void setCircumference(double circumference) throws InvalidStatException {
    if (circumference <= 0) {
      throw new InvalidStatException("Circumference must be positive");
    }
    this.circumference = circumference;
    this.isOfficialSize = (circumference >= 29.5 && circumference <= 30.0);
  }

  public int getGamesPlayed() {
    return gamesPlayed;
  }

  public boolean isOfficialSize() {
    return isOfficialSize;
  }

  public List<String> getPlayersUsed() {
    return new ArrayList<>(playersUsed);
  }

  public Position getPrimaryPosition() {
    return primaryPosition;
  }

  public void setPrimaryPosition(Position primaryPosition) {
    this.primaryPosition = primaryPosition;
  }

  @Override
  public String toString() {
    return String.format(
        "Basketball{brand='%s', circumference=%.1f, gamesPlayed=%d, "
            + "isOfficialSize=%s, primaryPosition=%s, playersUsed=%d}",
        brand,
        circumference,
        gamesPlayed,
        isOfficialSize,
        primaryPosition.getFullName(),
        playersUsed.size());
  }
}
