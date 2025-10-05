package com.codedifferently.lesson16.taliacrockett;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AnimeGenreTest {
  @Test
  public void testDisplayName() {
    AnimeGenre genre = AnimeGenre.SHONEN;
    assertEquals("Shonen", genre.getDisplayName());
  }

  @Test
  public void testToString() {
    AnimeGenre genre = AnimeGenre.FANTASY;
    assertEquals("Fantasy", genre.toString());
  }
}
