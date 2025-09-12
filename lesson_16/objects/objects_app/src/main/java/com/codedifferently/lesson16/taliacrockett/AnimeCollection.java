package com.codedifferently.lesson16.taliacrockett;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection class that manages multiple Anime objects. This class demonstrates advanced
 * object-oriented principles and meets all assignment requirements.
 */
public class AnimeCollection {
  // Member variables (5+ variables of 3+ different types)
  private String collectionName;
  private final List<Anime> animeList;
  private double averageRating;
  private boolean isPublic;
  private String ownerName;
  private AnimeGenre favoriteGenre;

  // Constructor
  public AnimeCollection(String collectionName, String ownerName) {
    this.collectionName = collectionName;
    this.ownerName = ownerName;
    this.animeList = new ArrayList<>();
    this.isPublic = false;
    this.averageRating = 0.0;
    this.favoriteGenre = null;
  }

  public void addAnime(Anime anime) throws AnimeNotFoundException {
    if (anime == null) {
      throw new AnimeNotFoundException("Cannot add null anime to collection");
    }

    animeList.add(anime);
    updateAverageRating();
    updateFavoriteGenre();
  }

  /**
   * Function 2: Uses collection member variable and loop Searches for anime by title using a loop
   */
  public Anime findAnimeByTitle(String title) throws AnimeNotFoundException {
    if (title == null || title.trim().isEmpty()) {
      throw new AnimeNotFoundException("Search title cannot be null or empty");
    }

    // Using enhanced for loop
    for (Anime anime : animeList) {
      if (anime.getTitle().equalsIgnoreCase(title.trim())) {
        return anime;
      }
    }

    throw new AnimeNotFoundException("Anime with title '" + title + "' not found in collection");
  }

  /**
   * Function 3: Uses collection, loop, and conditional Gets all anime with rating above a threshold
   */
  public List<Anime> getHighRatedAnime(double minRating) {
    List<Anime> highRated = new ArrayList<>();

    // Using traditional for loop
    for (int i = 0; i < animeList.size(); i++) {
      Anime anime = animeList.get(i);
      if (anime.getRating() >= minRating) {
        highRated.add(anime);
      }
    }

    return highRated;
  }

  /**
   * Additional function: Uses collection and conditional expression Removes anime from collection
   */
  public boolean removeAnime(String title) throws AnimeNotFoundException {
    Anime animeToRemove = findAnimeByTitle(title);
    boolean removed = animeList.remove(animeToRemove);

    if (removed) {
      updateAverageRating();
      updateFavoriteGenre();
    }

    return removed;
  }

  /** Helper function: Uses loop to calculate average rating */
  private void updateAverageRating() {
    if (animeList.isEmpty()) {
      averageRating = 0.0;
      return;
    }

    double sum = 0.0;
    for (Anime anime : animeList) {
      sum += anime.getRating();
    }
    averageRating = sum / animeList.size();
  }

  /** Helper function: Uses loop and conditional to find most common genre */
  private void updateFavoriteGenre() {
    if (animeList.isEmpty()) {
      favoriteGenre = null;
      return;
    }

    // Simple approach: find the first genre that appears most
    AnimeGenre mostCommon = null;
    int maxCount = 0;

    for (AnimeGenre genre : AnimeGenre.values()) {
      int count = 0;
      for (Anime anime : animeList) {
        if (anime.getGenre() == genre) {
          count++;
        }
      }
      if (count > maxCount) {
        maxCount = count;
        mostCommon = genre;
      }
    }

    favoriteGenre = mostCommon;
  }

  // Getters and Setters
  public String getCollectionName() {
    return collectionName;
  }

  public void setCollectionName(String collectionName) {
    this.collectionName = collectionName;
  }

  public List<Anime> getAnimeList() {
    return new ArrayList<>(animeList);
  }

  public List<Anime> getAnimeByGenre(AnimeGenre genre) {
    List<Anime> genreList = new ArrayList<>();
    for (Anime anime : animeList) {
      if (anime.getGenre() == genre) {
        genreList.add(anime);
      }
    }
    return genreList;
  }

  public double getAverageRating() {
    return averageRating;
  }

  public boolean isPublic() {
    return isPublic;
  }

  public void setPublic(boolean isPublic) {
    this.isPublic = isPublic;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }

  public AnimeGenre getFavoriteGenre() {
    return favoriteGenre;
  }

  public int getSize() {
    return animeList.size();
  }

  @Override
  public String toString() {
    return String.format(
        "AnimeCollection{name='%s', owner='%s', size=%d, avgRating=%.1f, favoriteGenre=%s}",
        collectionName, ownerName, animeList.size(), averageRating, favoriteGenre);
  }
}
