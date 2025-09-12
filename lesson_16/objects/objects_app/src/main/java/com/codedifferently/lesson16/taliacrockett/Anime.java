package com.codedifferently.lesson16.taliacrockett;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an anime with various properties and behaviors. This class meets the assignment
 * requirements for a custom data type.
 */
public class Anime {
  private String title; 
  private int episodes; 
  private double rating; 
  private boolean isCompleted; 
  private List<String> characters; 
  private AnimeGenre genre; 
  private String studio; 

  // Constructor
  public Anime(String title, int episodes, double rating, AnimeGenre genre, String studio) {
    this.title = title;
    this.episodes = episodes;
    this.rating = rating;
    this.genre = genre;
    this.studio = studio;
    this.isCompleted = false;
    this.characters = new ArrayList<>();
  }

  // Member functions (3+ functions required)

  public Anime(String title, AnimeGenre genre, double rating) {
    this.title = title;
    this.episodes = 0; // Default value
    this.rating = rating;
    this.genre = genre;
    this.studio = ""; // Default value
    this.isCompleted = false;
    this.characters = new ArrayList<>();
  }

  /**
   * Function 1: Uses conditional expression Determines if the anime is worth watching based on
   * rating
   */
  public String getRecommendation() {
    return rating >= 8.0
        ? "Highly Recommended!"
        : rating >= 6.0 ? "Worth Watching" : "Skip This One";
  }

  /** Function 2: Uses collection member variable Adds a character to the anime's character list */
  public void addCharacter(String characterName) {
    if (characterName != null && !characterName.trim().isEmpty()) {
      characters.add(characterName);
    }
  }

  /** Function 3: Uses a loop Searches for a character in the character list */
  public boolean hasCharacter(String characterName) {
    for (String character : characters) {
      if (character.equalsIgnoreCase(characterName)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Additional function: Uses collection and conditional Gets character count with status message
   */
  public String getCharacterSummary() {
    return "Characters: " + String.join(", ", characters);
  }

  // Getters and Setters
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getEpisodes() {
    return episodes;
  }

  public void setEpisodes(int episodes) {
    this.episodes = episodes;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  public boolean isCompleted() {
    return isCompleted;
  }

  public void setCompleted(boolean completed) {
    isCompleted = completed;
  }

  public List<String> getCharacters() {
    return new ArrayList<>(characters); // Return defensive copy
  }

  public AnimeGenre getGenre() {
    return genre;
  }

  public void setGenre(AnimeGenre genre) {
    this.genre = genre;
  }

  public String getStudio() {
    return studio;
  }

  public void setStudio(String studio) {
    this.studio = studio;
  }

  @Override
  public String toString() {
    return String.format(
        "Anime{title='%s', episodes=%d, rating=%.1f, genre=%s, studio='%s', completed=%s}",
        title, episodes, rating, genre, studio, isCompleted);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Anime anime = (Anime) obj;
    return title != null ? title.equals(anime.title) : anime.title == null;
  }

  @Override
  public int hashCode() {
    return title != null ? title.hashCode() : 0;
  }
}
