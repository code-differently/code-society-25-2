package com.codedifferently.lesson16.nicolejackson;

import java.util.ArrayList;

public class BestFriend {
  // Member variables
  private String name;
  private int age;
  private boolean livesNearby;
  private double trustScore;
  private ArrayList<String> favoriteActivities;
  private FriendLevel level;

  public BestFriend(
      String name,
      int age,
      boolean livesNearby,
      double trustScore,
      ArrayList<String> favoriteActivities,
      FriendLevel level) {
    this.name = name;
    this.age = age;
    this.livesNearby = livesNearby;
    this.trustScore = trustScore;
    this.favoriteActivities =
        favoriteActivities != null ? new ArrayList<>(favoriteActivities) : new ArrayList<>();
    this.level = level;
  }

  public String getName() {
    return this.name;
  }

  public int getAge() {
    return this.age;
  }

  public boolean getLivesNearby() {
    return this.livesNearby;
  }

  public double getTrustScore() {
    return this.trustScore;
  }

  public ArrayList<String> getFavoriteActivities() {
    return new ArrayList<>(this.favoriteActivities);
  }

  public FriendLevel getLevel() {
    return this.level;
  }

  public boolean isTrustworthy() {
    return trustScore >= 75;
  }

  public void showActivities() {
    System.out.println(name + "'s favorite activities:");
    for (String activity : favoriteActivities) {
      System.out.println("- " + activity);
    }
  }

  public void addFavoriteActivity(String activity) throws InvalidActivityException {
    if (activity == null || activity.isEmpty()) {
      throw new InvalidActivityException("Activity cannot be empty!");
    }
    favoriteActivities.add(activity);
  }

  public void describeFriendLevel() {
    switch (level) {
      case BEST:
        System.out.println(name + " is your BEST friend!");
        break;
      case GOOD:
        System.out.println(name + " is a GOOD friend.");
        break;
      case CASUAL:
        System.out.println(name + " is a CASUAL friend.");
        break;
    }
  }
}
