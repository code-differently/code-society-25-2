package com.codedifferently.lesson16.nicolejackson;

import java.util.ArrayList;

public class BestFriend {
  private String name;
  private int age;
  private boolean livesNearby;
  private ArrayList<String> favoriteActivities;
  private double trustScore;

  public BestFriend(
      String name,
      int age,
      boolean livesNearby,
      double trustScore,
      ArrayList<String> favoriteActivities) {
    this.name = name;
    this.age = age;
    this.livesNearby = livesNearby;
    this.trustScore = trustScore;
    this.favoriteActivities = new ArrayList<>();
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
    return this.favoriteActivities;
  }

  public boolean isTrustworthy() {
    return trustScore >= 75 ? true : false;
  }

  public void addFavoriteActivity(String activity) throws InvalidActivityException {
    if (activity == null || activity.isEmpty()) {
      throw new InvalidActivityException("Activity cannot be empty!");
    }
    favoriteActivities.add(activity);
  }

  public void showActivities() {
    System.out.println(name + "'s favorite activities:");
    for (String activity : favoriteActivities) {
      System.out.println("- " + activity);
    }
  }

}
