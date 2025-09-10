package com.codedifferently.lesson16.library;

import java.util.ArrayList;
import java.util.List;

enum BookStatus {
    AVAILABLE,
    CHECKED_OUT,
    RESERVED,
    DAMAGED
}


class BookException extends Exception {
    public BookException(String message) {
        super(message);
}

public class Book {
    
    private String title;           
    private String author;           
    private int pages;              
    private double price;           
    private boolean isAvailable;    
    private BookStatus status;      
    private List<String> genres;    

    public Book(String title, String author, int pages, double price) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.price = price;
        this.isAvailable = true;           
        this.status = BookStatus.AVAILABLE; // Default status
        this.genres = new ArrayList<>();    
    }
    // Function 1: Uses conditional expression 
    public String getAvailabilityInfo() {
        return isAvailable ? "This book is available for checkout" : "This book is currently unavailable";
    }
    // Function 2: Uses collection member variable 
    public void addGenre(String genre) throws BookException {
        if (genre == null || genre.trim().isEmpty()) {
            throw new BookException("Genre cannot be null or empty");
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
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPages() { return pages; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return isAvailable; }
}
}