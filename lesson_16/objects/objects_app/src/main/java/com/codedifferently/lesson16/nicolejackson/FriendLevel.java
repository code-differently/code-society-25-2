package com.codedifferently.lesson16.nicolejackson;

public enum FriendLevel {
  BEST,
  GOOD,
  CASUAL;

  public String getDescription() {
    switch (this) {
      case BEST:
        return "Best friend";
      case GOOD:
        return "Good friend";
      case CASUAL:
        return "Casual friend";
      default:
        return "Unknown";
    }
  }
}
