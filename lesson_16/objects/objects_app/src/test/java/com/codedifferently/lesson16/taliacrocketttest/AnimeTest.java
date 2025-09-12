package com.codedifferently.lesson16.taliacrocketttest;

import static org.junit.jupiter.api.Assertions.*;

import com.codedifferently.lesson16.taliacrockett.Anime;
import com.codedifferently.lesson16.taliacrockett.AnimeGenre;
import org.junit.jupiter.api.Test;

public class AnimeTest {
  // Test methods for Anime class
  @Test
  public void testGetRecommendation() {
    Anime anime1 = new Anime("Test Anime 1", 12, 8.5, AnimeGenre.SHONEN, "Studio A");
    assertEquals("Highly Recommended!", anime1.getRecommendation());

    Anime anime2 = new Anime("Test Anime 2", 24, 7.0, AnimeGenre.SHOUJO, "Studio B");
    assertEquals("Worth Watching", anime2.getRecommendation());

    Anime anime3 = new Anime("Test Anime 3", 10, 5.5, AnimeGenre.COMEDY, "Studio C");
    assertEquals("Skip This One", anime3.getRecommendation());
  }

  @Test
  public void testAddCharacter() {
    Anime anime = new Anime("Test Anime", 12, 8.0, AnimeGenre.FANTASY, "Studio D");
    anime.addCharacter("Character A");
    anime.addCharacter("Character B");

    assertTrue(anime.hasCharacter("Character A"));
    assertTrue(anime.hasCharacter("Character B"));
    assertFalse(anime.hasCharacter("Character C"));
  }

  @Test
  public void testHasCharacter() {
    Anime anime = new Anime("Test Anime", 12, 8.0, AnimeGenre.FANTASY, "Studio D");
    anime.addCharacter("Character A");
    assertTrue(anime.hasCharacter("Character A"));
    assertFalse(anime.hasCharacter("Character B"));
  }

  @Test
  public void testGetCharacterSummary() {
    Anime anime = new Anime("Test Anime", 12, 8.0, AnimeGenre.FANTASY, "Studio D");
    anime.addCharacter("Character A");
    anime.addCharacter("Character B");
    String summary = anime.getCharacterSummary();
    assertEquals("Characters: Character A, Character B", summary);
  }

  @Test
  public void testGetTitle() {
    Anime anime = new Anime("Test Anime", 12, 8.0, AnimeGenre.FANTASY, "Studio D");
    assertEquals("Test Anime", anime.getTitle());
  }

  @Test
  public void testSetTitle() {
    Anime anime = new Anime("Old Title", 12, 8.0, AnimeGenre.FANTASY, "Studio D");
    anime.setTitle("New Title");
    assertEquals("New Title", anime.getTitle());
  }

  @Test
  public void testGetEpisodes() {
    Anime anime = new Anime("Test Anime", 12, 8.0, AnimeGenre.FANTASY, "Studio D");
    assertEquals(12, anime.getEpisodes());
  }

  @Test
  public void testSetEpisodes() {
    Anime anime = new Anime("Test Anime", 12, 8.0, AnimeGenre.FANTASY, "Studio D");
    anime.setEpisodes(24);
    assertEquals(24, anime.getEpisodes());
  }

  @Test
  public void testGetRating() {
    Anime anime = new Anime("Test Anime", 12, 8.0, AnimeGenre.FANTASY, "Studio D");
    assertEquals(8.0, anime.getRating());
  }

  @Test
  public void testSetRating() {
    Anime anime = new Anime("Test Anime", 12, 8.0, AnimeGenre.FANTASY, "Studio D");
    anime.setRating(9.5);
    assertEquals(9.5, anime.getRating());
  }

  @Test
  public void testIsCompleted() {
    Anime anime = new Anime("Test Anime", 12, 8.0, AnimeGenre.FANTASY, "Studio D");
    assertFalse(anime.isCompleted());
    anime.setCompleted(true);
    assertTrue(anime.isCompleted());
  }

  @Test
  public void testSetCompleted() {
    Anime anime = new Anime("Test Anime", 12, 8.0, AnimeGenre.FANTASY, "Studio D");
    anime.setCompleted(true);
    assertTrue(anime.isCompleted());
    anime.setCompleted(false);
    assertFalse(anime.isCompleted());
  }

  @Test
  public void testGetCharacters() {
    Anime anime = new Anime("Test Anime", 12, 8.0, AnimeGenre.FANTASY, "Studio D");
    anime.addCharacter("Character A");
    anime.addCharacter("Character B");
    assertEquals(2, anime.getCharacters().size());
    assertTrue(anime.getCharacters().contains("Character A"));
    assertTrue(anime.getCharacters().contains("Character B"));
  }

  @Test
  public void testGetGenre() {
    Anime anime = new Anime("Test Anime", 12, 8.0, AnimeGenre.FANTASY, "Studio D");
    assertEquals(AnimeGenre.FANTASY, anime.getGenre());
  }

  @Test
  public void testSetGenre() {
    Anime anime = new Anime("Test Anime", 12, 8.0, AnimeGenre.FANTASY, "Studio D");
    anime.setGenre(AnimeGenre.HORROR);
    assertEquals(AnimeGenre.HORROR, anime.getGenre());
  }

  @Test
  public void testGetStudio() {
    Anime anime = new Anime("Test Anime", 12, 8.0, AnimeGenre.FANTASY, "Studio D");
    assertEquals("Studio D", anime.getStudio());
  }

  @Test
  public void testSetStudio() {
    Anime anime = new Anime("Test Anime", 12, 8.0, AnimeGenre.FANTASY, "Studio D");
    anime.setStudio("Studio E");
    assertEquals("Studio E", anime.getStudio());
  }

  @Test
  public void testToString() {
    Anime anime = new Anime("Test Anime", 12, 8.0, AnimeGenre.FANTASY, "Studio D");
    String expected = "Anime{title='Test Anime', episodes=12, rating=8.0, genre=FANTASY, studio='Studio D', completed=false}";
    assertEquals(expected, anime.toString());
  }
}
