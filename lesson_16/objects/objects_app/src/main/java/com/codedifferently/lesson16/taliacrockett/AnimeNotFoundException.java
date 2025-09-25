package com.codedifferently.lesson16.taliacrockett;

/** Custom exception thrown when an anime is not found in the collection. */
public class AnimeNotFoundException extends Exception {
  public AnimeNotFoundException(String message) {
    super(message);
  }
}
