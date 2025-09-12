package com.codedifferently.lesson16.library;

/** Enum representing different genres of books in a library. */
public enum BookGenre {
  FICTION("Fiction"),
  NON_FICTION("Non-Fiction"),
  MYSTERY("Mystery"),
  SCIENCE_FICTION("Science Fiction"),
  ROMANCE("Romance"),
  FANTASY("Fantasy"),
  BIOGRAPHY("Biography"),
  HISTORY("History"),
  SCIENCE("Science"),
  CHILDREN("Children's Books");

  private final String displayName;

  BookGenre(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }

  @Override
  public String toString() {
    return displayName;
  }
}
