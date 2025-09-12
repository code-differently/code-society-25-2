package com.codedifferently.lesson16.library;

/** Custom exception thrown when a book is not found in the library. */
public class BookNotFoundException extends Exception {

  private final String isbn;

  public BookNotFoundException(String isbn) {
    super("Book with ISBN '" + isbn + "' was not found in the library.");
    this.isbn = isbn;
  }

  public BookNotFoundException(String isbn, String message) {
    super(message);
    this.isbn = isbn;
  }

  public String getIsbn() {
    return isbn;
  }
}
