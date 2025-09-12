package com.codedifferently.lesson16.taliacrocketttest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codedifferently.lesson16.taliacrockett.Anime;
import com.codedifferently.lesson16.taliacrockett.AnimeCollection;
import com.codedifferently.lesson16.taliacrockett.AnimeGenre;
import com.codedifferently.lesson16.taliacrockett.AnimeNotFoundException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test class for AnimeCollection. Contains 5+ test methods to meet assignment requirements. */
public class AnimeCollectionTest {
  private AnimeCollection collection;
  private Anime naruto;
  private Anime demonSlayer;
  private Anime attackOnTitan;

  @BeforeEach
  public void setUp() throws AnimeNotFoundException {
    collection = new AnimeCollection("Test Collection", "A test anime collection");
    naruto = new Anime("Naruto", 720, 8.5, AnimeGenre.SHONEN, "Pierrot");
    demonSlayer = new Anime("Demon Slayer", 26, 9.2, AnimeGenre.SHONEN, "Ufotable");
    attackOnTitan = new Anime("Attack on Titan", 87, 9.0, AnimeGenre.ACTION, "Mappa");
  }

  @Test
  void testAddAnime() throws AnimeNotFoundException {
    assertEquals(0, collection.getSize());

    collection.addAnime(naruto);
    assertEquals(1, collection.getSize());
    assertEquals(8.5, collection.getAverageRating(), 0.1);
    assertEquals(AnimeGenre.SHONEN, collection.getFavoriteGenre());
  }

  @Test
  void testFindAnimeByTitle() throws AnimeNotFoundException {
    collection.addAnime(naruto);
    collection.addAnime(demonSlayer);

    Anime found = collection.findAnimeByTitle("Naruto");
    assertEquals(naruto, found);
    assertEquals("Naruto", found.getTitle());

    // Test case insensitive search
    Anime foundCaseInsensitive = collection.findAnimeByTitle("demon slayer");
    assertEquals(demonSlayer, foundCaseInsensitive);
  }

  @Test
  void testFindAnimeByTitleNotFound() {
    AnimeNotFoundException exception =
        assertThrows(AnimeNotFoundException.class, () -> collection.findAnimeByTitle("One Piece"));
    assertEquals("Anime with title 'One Piece' not found in collection", exception.getMessage());
  }

  @Test
  void testGetHighRatedAnime() throws AnimeNotFoundException {
    collection.addAnime(naruto); // 8.5 rating
    collection.addAnime(demonSlayer); // 9.2 rating
    collection.addAnime(attackOnTitan); // 9.0 rating

    List<Anime> highRated = collection.getHighRatedAnime(9.0);
    assertEquals(2, highRated.size());
    assertTrue(highRated.contains(demonSlayer));
    assertTrue(highRated.contains(attackOnTitan));
    assertFalse(highRated.contains(naruto));
  }

  @Test
  void testRemoveAnime() throws AnimeNotFoundException {
    collection.addAnime(naruto);
    collection.addAnime(demonSlayer);

    assertEquals(2, collection.getSize());

    boolean removed = collection.removeAnime("Naruto");
    assertTrue(removed);
    assertEquals(1, collection.getSize());
    assertEquals(9.2, collection.getAverageRating(), 0.1);

    // Test removing non-existent anime
    AnimeNotFoundException exception =
        assertThrows(AnimeNotFoundException.class, () -> collection.removeAnime("Non-existent"));
    assertEquals("Anime with title 'Non-existent' not found in collection", exception.getMessage());
  }

  /** Test 7: Test filtering by genre */
  @Test
  void testGetAnimeByGenre() throws AnimeNotFoundException {
    collection.addAnime(naruto);
    collection.addAnime(demonSlayer);
    collection.addAnime(attackOnTitan);

    List<Anime> shonenAnime = collection.getAnimeByGenre(AnimeGenre.SHONEN);
    assertEquals(2, shonenAnime.size());
    assertTrue(shonenAnime.contains(naruto));
    assertTrue(shonenAnime.contains(demonSlayer));

    List<Anime> actionAnime = collection.getAnimeByGenre(AnimeGenre.ACTION);
    assertEquals(1, actionAnime.size());
    assertTrue(actionAnime.contains(attackOnTitan));
  }

  @Test
  public void testNullAnimeHandling() {
    AnimeNotFoundException exception =
        assertThrows(AnimeNotFoundException.class, () -> collection.addAnime(null));
    assertEquals("Cannot add null anime to collection", exception.getMessage());
  }

  /** Test 10: Test average rating calculation */
  @Test
  void testAverageRatingCalculation() throws AnimeNotFoundException {
    assertEquals(0.0, collection.getAverageRating());

    collection.addAnime(naruto); // 8.5
    assertEquals(8.5, collection.getAverageRating(), 8.5);

    collection.addAnime(demonSlayer); // 9.2
    assertEquals(8.85, collection.getAverageRating(), 9.2); // (8.5 + 9.2) / 2

    collection.addAnime(attackOnTitan); // 9.0
    assertEquals(8.9, collection.getAverageRating(), 9.0); // (8.5 + 9.2 + 9.0) / 3
  }
}
