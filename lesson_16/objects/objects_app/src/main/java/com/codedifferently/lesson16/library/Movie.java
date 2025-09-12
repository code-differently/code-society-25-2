package com.codedifferently.lesson16.library;

import java.util.ArrayList;
import java.util.List;

enum MovieStatus {
  AVAILABLE,
  CHECKED_OUT,
  RESERVED,
  DAMAGED
}

class MovieException extends Exception {
  public MovieException(String message) {
    super(message);
  }
}

public class Movie {

  private String title;
  private String director;
  private int duration;
  private double price;
  private boolean isAvailable;
  private MovieStatus status;
  private List<String> genres;

  public Movie(String title, String director, int duration, double price) {
    this.title = title;
    this.director = director;
    this.duration = duration;
    this.price = price;
    this.isAvailable = true;
    this.status = MovieStatus.AVAILABLE; // Default status
    this.genres = new ArrayList<>();
  }

  // Function 1: Uses conditional expression
  public String getAvailabilityInfo() {
    return isAvailable
        ? "This movie is available for checkout"
        : "This movie is currently unavailable";
  }

  // Function 2: Uses collection member variable
  public void addGenre(String genre) throws MovieException {
    if (genre == null || genre.trim().isEmpty()) {
      throw new MovieException("Genre cannot be null or empty");
    }
    if (!genres.contains(genre)) {
      genres.add(genre);
    }
  }

  public List<String> getGenres() {
    return new ArrayList<>(genres); // Return a copy to protect original list
  }

  // Function 3: Uses a loop
  public String getGenreDisplay() {
    if (genres.isEmpty()) {
      return "No genres assigned";
    }

    StringBuilder display = new StringBuilder("Genres: ");
    for (int i = 0; i < genres.size(); i++) {
      display.append(genres.get(i));
      // Add comma and space if not the last genre
      if (i < genres.size() - 1) {
        display.append(", ");
      }
    }
    return display.toString();
  }

  // Getter methods for testing
  public String getTitle() {
    return title;
  }

  public String getDirector() {
    return director;
  }

  public int getDuration() {
    return duration;
  }

  public double getPrice() {
    return price;
  }

  public boolean isAvailable() {
    return isAvailable;
  }
}
