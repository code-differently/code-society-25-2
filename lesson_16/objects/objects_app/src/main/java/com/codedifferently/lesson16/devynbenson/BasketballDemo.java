package com.codedifferently.lesson16.devynbenson;

/** Demo class to showcase the Basketball class functionality */
public class BasketballDemo {

  public static void main(String[] args) {
    try {

      Basketball spalding = new Basketball("Spalding Official NBA", 29.7, Position.CENTER);
      Basketball wilson = new Basketball("Wilson Evolution", 29.5, Position.POINT_GUARD);

      System.out.println("=== NBA Basketball Demo ===\n");

      System.out.println("Created basketballs:");
      System.out.println("1. " + spalding);
      System.out.println("2. " + wilson);
      System.out.println();

      System.out.println("Adding NBA stars to the Spalding basketball:");
      spalding.addPlayerUsage("Shaquille O'Neal");
      spalding.addPlayerUsage("Dwight Howard");
      spalding.addPlayerUsage("Anthony Davis");
      spalding.addPlayerUsage("Joel Embiid");
      System.out.println("Players added: " + spalding.getPlayerCount());
      System.out.println();

      System.out.println("Adding point guards to the Wilson basketball:");
      wilson.addPlayerUsage("Stephen Curry");
      wilson.addPlayerUsage("Chris Paul");
      wilson.addPlayerUsage("Russell Westbrook");
      System.out.println("Players added: " + wilson.getPlayerCount());
      System.out.println();

      System.out.println("Simulating game usage...");
      for (int i = 0; i < 25; i++) {
        spalding.incrementGamesPlayed();
      }
      for (int i = 0; i < 60; i++) {
        wilson.incrementGamesPlayed();
      }

      System.out.println("Performance ratings:");
      System.out.println(
          "Spalding (" + spalding.getGamesPlayed() + " games): " + spalding.getPerformanceRating());
      System.out.println(
          "Wilson (" + wilson.getGamesPlayed() + " games): " + wilson.getPerformanceRating());
      System.out.println();

      System.out.println("=== Spalding Basketball Players Report ===");
      System.out.println(spalding.getPlayersReport());

      System.out.println("=== Wilson Basketball Players Report ===");
      System.out.println(wilson.getPlayersReport());

      System.out.println("=== Basketball Specifications ===");
      System.out.println(
          "Spalding: "
              + spalding.getCircumference()
              + " inches - Official Size: "
              + spalding.isOfficialSize());
      System.out.println(
          "Wilson: "
              + wilson.getCircumference()
              + " inches - Official Size: "
              + wilson.isOfficialSize());
      System.out.println();

      System.out.println("=== Position Information ===");
      System.out.println(
          "Spalding Primary Position: "
              + spalding.getPrimaryPosition().getFullName()
              + " ("
              + spalding.getPrimaryPosition().getAbbreviation()
              + ")");
      System.out.println(
          "Wilson Primary Position: "
              + wilson.getPrimaryPosition().getFullName()
              + " ("
              + wilson.getPrimaryPosition().getAbbreviation()
              + ")");

    } catch (InvalidStatException e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
