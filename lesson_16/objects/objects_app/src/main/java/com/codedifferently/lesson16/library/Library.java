package com.codedifferently.lesson16.library;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a library management system that handles books, members, and borrowing operations.
 * This class demonstrates various OOP principles and meets all assignment requirements.
 */
public class Library {
  // Member variables (at least 5, of at least 3 different types)
  private String libraryName; // String type
  private int maxBooksPerMember; // int type
  private boolean isOpen; // boolean type
  private double lateFeePerDay; // double type
  private List<Book> books; // Collection type (ArrayList)
  private Map<String, LocalDate> borrowedBooks; // Collection type (HashMap)
  private BookGenre mostPopularGenre; // Enum type

  /** Constructor for the Library class. */
  public Library(String libraryName, int maxBooksPerMember, double lateFeePerDay) {
    this.libraryName = libraryName;
    this.maxBooksPerMember = maxBooksPerMember;
    this.lateFeePerDay = lateFeePerDay;
    this.isOpen = true;
    this.books = new ArrayList<>();
    this.borrowedBooks = new HashMap<>();
    this.mostPopularGenre = BookGenre.FICTION; // Default
  }

  /** Adds a book to the library collection. This function uses the collection member variable. */
  public void addBook(Book book) {
    if (book != null && !books.contains(book)) {
      books.add(book);
    }
  }

  /** Finds books by genre using a loop. This function demonstrates the use of a loop. */
  public List<Book> findBooksByGenre(BookGenre genre) {
    List<Book> result = new ArrayList<>();

    // Using a loop as required
    for (Book book : books) {
      if (book.getGenre() == genre) {
        result.add(book);
      }
    }

    return result;
  }

  /**
   * Borrows a book from the library. This function uses a conditional expression and throws a
   * custom exception.
   */
  public void borrowBook(String isbn, String memberId) throws BookNotFoundException {
    // Conditional expression to check if library is open
    if (!isOpen) {
      throw new IllegalStateException("Library is currently closed");
    }

    Book bookToBorrow = null;

    // Find the book
    for (Book book : books) {
      if (book.getIsbn().equals(isbn)) {
        bookToBorrow = book;
        break;
      }
    }

    // Custom exception usage
    if (bookToBorrow == null) {
      throw new BookNotFoundException(isbn);
    }

    // Conditional expression for availability
    if (!bookToBorrow.isAvailable()) {
      throw new BookNotFoundException(isbn, "Book is currently not available");
    }

    // Conditional expression for borrowing limit
    long currentBorrowedCount =
        borrowedBooks.values().stream().filter(date -> date != null).count();

    if (currentBorrowedCount >= maxBooksPerMember) {
      throw new IllegalStateException("Maximum borrowing limit reached");
    }

    // Complete the borrowing process
    bookToBorrow.setAvailable(false);
    borrowedBooks.put(isbn, LocalDate.now());
  }

  /** Returns a borrowed book to the library. */
  public double returnBook(String isbn) throws BookNotFoundException {
    if (!borrowedBooks.containsKey(isbn)) {
      throw new BookNotFoundException(isbn, "Book was not borrowed from this library");
    }

    Book bookToReturn = null;
    for (Book book : books) {
      if (book.getIsbn().equals(isbn)) {
        bookToReturn = book;
        break;
      }
    }

    if (bookToReturn == null) {
      throw new BookNotFoundException(isbn);
    }

    // Calculate late fee using conditional expression
    LocalDate borrowDate = borrowedBooks.get(isbn);
    LocalDate today = LocalDate.now();
    long daysLate = today.toEpochDay() - borrowDate.toEpochDay() - 14; // 14-day loan period

    double lateFee = daysLate > 0 ? daysLate * lateFeePerDay : 0.0;

    // Return the book
    bookToReturn.setAvailable(true);
    borrowedBooks.remove(isbn);

    return lateFee;
  }

  /** Gets library statistics using loops and conditional expressions. */
  public String getLibraryStatistics() {
    int totalBooks = books.size();
    int availableBooks = 0;
    int borrowedBooksCount = 0;
    Map<BookGenre, Integer> genreCount = new HashMap<>();

    // Using loops and conditional expressions
    for (Book book : books) {
      if (book.isAvailable()) {
        availableBooks++;
      } else {
        borrowedBooksCount++;
      }

      // Count books by genre
      genreCount.put(book.getGenre(), genreCount.getOrDefault(book.getGenre(), 0) + 1);
    }

    // Find most popular genre using loop
    BookGenre popular = BookGenre.FICTION;
    int maxCount = 0;
    for (Map.Entry<BookGenre, Integer> entry : genreCount.entrySet()) {
      if (entry.getValue() > maxCount) {
        maxCount = entry.getValue();
        popular = entry.getKey();
      }
    }
    this.mostPopularGenre = popular;

    return String.format(
        "Library: %s\nTotal Books: %d\nAvailable: %d\nBorrowed: %d\nMost Popular Genre: %s",
        libraryName, totalBooks, availableBooks, borrowedBooksCount, mostPopularGenre);
  }

  // Getters and additional methods
  public String getLibraryName() {
    return libraryName;
  }

  public int getMaxBooksPerMember() {
    return maxBooksPerMember;
  }

  public boolean isOpen() {
    return isOpen;
  }

  public void setOpen(boolean open) {
    isOpen = open;
  }

  public double getLateFeePerDay() {
    return lateFeePerDay;
  }

  public List<Book> getAllBooks() {
    return new ArrayList<>(books); // Return a copy to maintain encapsulation
  }

  public int getTotalBooksCount() {
    return books.size();
  }

  public BookGenre getMostPopularGenre() {
    return mostPopularGenre;
  }
}
