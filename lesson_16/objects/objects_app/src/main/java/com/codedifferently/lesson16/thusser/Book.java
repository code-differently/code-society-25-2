package com.codedifferently.lesson16.thusser;

/** Represents a book in the library */
public class Book {
  private final String title;
  private final String author;
  private final String isbn;
  private final BookGenre genre;
  private boolean isAvailable;

  public Book(String title, String author, String isbn, BookGenre genre) {
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.genre = genre;
    this.isAvailable = true;
  }

  // Getters and setters
  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public String getIsbn() {
    return isbn;
  }

  public BookGenre getGenre() {
    return genre;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean available) {
    this.isAvailable = available;
  }

  @Override
  public String toString() {
    return String.format(
        "Book{title='%s', author='%s', isbn='%s', genre=%s, available=%s}",
        title, author, isbn, genre, isAvailable);
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
