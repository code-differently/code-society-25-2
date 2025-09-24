package com.codedifferently.lesson16.library;

/** Represents a book in the library system. */
public class Book {
  private String isbn;
  private String title;
  private String author;
  private BookGenre genre;
  private boolean isAvailable;

  public Book(String isbn, String title, String author, BookGenre genre) {
    this.isbn = isbn;
    this.title = title;
    this.author = author;
    this.genre = genre;
    this.isAvailable = true; // New books are available by default
  }

  // Getters
  public String getIsbn() {
    return isbn;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public BookGenre getGenre() {
    return genre;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  // Setters
  public void setAvailable(boolean available) {
    isAvailable = available;
  }

  @Override
  public String toString() {
    return String.format(
        "Book{isbn='%s', title='%s', author='%s', genre=%s, available=%s}",
        isbn, title, author, genre, isAvailable);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Book book = (Book) obj;
    return isbn.equals(book.isbn);
  }

  @Override
  public int hashCode() {
    return isbn.hashCode();
  }
}
