package com.codedifferently.lesson16.taliacrockett;

public enum AnimeGenre {
  SHONEN("Shonen"),
  SHOUJO("Shoujo"),
  SEINEN("Seinen"),
  JOSEI("Josei"),
  ISEKAI("Isekai"),
  MECHA("Mecha"),
  SLICE_OF_LIFE("Slice of Life"),
  ROMANCE("Romance"),
  ACTION("Action"),
  COMEDY("Comedy"),
  DRAMA("Drama"),
  FANTASY("Fantasy"),
  SUPERNATURAL("Supernatural"),
  THRILLER("Thriller"),
  HORROR("Horror");

  public String displayName;

  AnimeGenre(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    if (displayName == null) {
      return "Unknown Genre";
    }
    return displayName;
  }

  @Override
  public String toString() {
    return displayName;
  }
}
