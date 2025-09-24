package com.codedifferently.lesson16.devynbenson;

/** Enum representing basketball player positions in the NBA */
public enum Position {
  POINT_GUARD("PG", "Point Guard"),
  SHOOTING_GUARD("SG", "Shooting Guard"),
  SMALL_FORWARD("SF", "Small Forward"),
  POWER_FORWARD("PF", "Power Forward"),
  CENTER("C", "Center");

  private final String abbreviation;
  private final String fullName;

  Position(String abbreviation, String fullName) {
    this.abbreviation = abbreviation;
    this.fullName = fullName;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public String getFullName() {
    return fullName;
  }
}
