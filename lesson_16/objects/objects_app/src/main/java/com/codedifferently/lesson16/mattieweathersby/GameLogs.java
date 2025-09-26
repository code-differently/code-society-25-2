package com.codedifferently.lesson16.mattieweathersby;

import java.util.ArrayList;
import java.util.List;

public class GameLogs {
  private String title;
  private final List<String> gameSystems;
  private double price;
  private String storeLocation;
  private int rating;

  public enum GameGenre {
    ACTION,
    ADVENTURE,
    RPG,
    SPORTS,
    PUZZLE,
    OTHER
  }

  public GameGenre genre;

  // Constructor
  public GameLogs(
      String title,
      String gameSystem,
      double price,
      String storeLocation,
      int rating,
      GameGenre genre) {
    this.title = title;
    this.gameSystems = new ArrayList<>();
    this.gameSystems.add(gameSystem);
    this.price = price;
    this.storeLocation = storeLocation;
    this.rating = rating;
    this.genre = genre;
  }

  // Setters
  public void setTitle(String title) {
    this.title = title;
  }

  // Instead of overwriting the list, append to it
  public void addGameSystem(String gameSystem) {
    this.gameSystems.add(gameSystem);
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setStoreLocation(String storeLocation) {
    this.storeLocation = storeLocation;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public void setGenre(GameGenre genre) {
    this.genre = genre;
  }

  // Getters
  public String getTitle() {
    return title;
  }

  public List<String> getGameSystems() {
    return gameSystems;
  }

  public double getPrice() {
    return price;
  }

  public String getStoreLocation() {
    return storeLocation;
  }

  public int getRating() {
    return rating;
  }

  public GameGenre getGenre() {
    return genre;
  }

  // functions

  public void checkTitle(String title) {
    if (title == null || title.isEmpty()) {
      throw new IllegalArgumentException("Title cannot be null or empty");
    }
  }

  public int checkRating() {
    if (rating >= 8) {
      System.out.println("This game is highly rated! " + rating);
    } else if (rating >= 5) {
      System.out.println("This game has an average rating. " + rating);
    } else {
      System.out.println("This game has a low rating. " + rating);
    }
    return rating;
  }

  public int getSystemCount() {
    return gameSystems.size();
  }

  public void printSystems() {
    for (String system : gameSystems) {
      String message =
          (system.equalsIgnoreCase("PlayStation"))
              ? "Popular system: " + system
              : "Other system: " + system;

      System.out.println(message);
    }
  }
}
