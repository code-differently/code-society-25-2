package com.codedifferently.lesson16.thusser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Represents a Library system with books and operations */
public class Library {
  // 5+ member variables of at least 3 different types (String, int, LocalDate, double, ArrayList)
  private final String name; // String type
  private final String address; // String type
  private int totalCapacity; // int type
  private final LocalDate establishedDate; // LocalDate type
  private double membershipFee; // double type
  private final ArrayList<Book> books; // Collection type (ArrayList)
  private final ArrayList<String> members; // Additional collection

  /** Constructor for Library */
  public Library(
      String name,
      String address,
      int totalCapacity,
      LocalDate establishedDate,
      double membershipFee) {
    this.name = name;
    this.address = address;
    this.totalCapacity = totalCapacity;
    this.establishedDate = establishedDate;
    this.membershipFee = membershipFee;
    this.books = new ArrayList<>();
    this.members = new ArrayList<>();
  }

  /** Adds a book to the library collection Uses collection member variable */
  public void addBook(Book book) throws LibraryException {
    if (book == null) {
      throw new LibraryException("Cannot add null book to library");
    }
    if (books.contains(book)) {
      throw new LibraryException("Book with ISBN " + book.getIsbn() + " already exists");
    }
    books.add(book);
  }

  /**
   * Finds books by genre using conditional expression and loop Uses conditional expression,
   * collection member variable, and loop
   */
  public List<Book> findBooksByGenre(BookGenre genre) throws LibraryException {
    if (genre == null) {
      throw new LibraryException("Genre cannot be null");
    }

    List<Book> result = new ArrayList<>();

    // Loop through all books
    for (Book book : books) {
      // Conditional expression
      if (book.getGenre() == genre) {
        result.add(book);
      }
    }

    return result;
  }

  /**
   * Calculates annual revenue based on membership count Uses conditional expression and collection
   * member variable
   */
  public double calculateAnnualRevenue() {
    // Conditional expression based on member count
    double discountRate = members.size() > 100 ? 0.1 : 0.0;
    double baseRevenue = members.size() * membershipFee * 12; // Monthly to annual
    return baseRevenue * (1 - discountRate);
  }

  /** Checks if library can accommodate more books Uses conditional expression */
  public boolean canAddMoreBooks() {
    return books.size() < totalCapacity;
  }

  /** Adds a member to the library Uses collection member variable */
  public void addMember(String memberName) throws LibraryException {
    if (memberName == null || memberName.trim().isEmpty()) {
      throw new LibraryException("Member name cannot be null or empty");
    }
    if (members.contains(memberName)) {
      throw new LibraryException("Member " + memberName + " already exists");
    }
    members.add(memberName);
  }

  /** Gets statistics about the library using loops Uses loop and collection member variables */
  public String getLibraryStatistics() {
    int availableBooks = 0;
    int checkedOutBooks = 0;

    // Loop to count available vs checked out books
    for (Book book : books) {
      if (book.isAvailable()) {
        availableBooks++;
      } else {
        checkedOutBooks++;
      }
    }

    return String.format(
        "Library: %s\nTotal Books: %d\nAvailable: %d\nChecked Out: %d\nMembers: %d\nEstablished: %s",
        name, books.size(), availableBooks, checkedOutBooks, members.size(), establishedDate);
  }

  // Getters
  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public int getTotalCapacity() {
    return totalCapacity;
  }

  public LocalDate getEstablishedDate() {
    return establishedDate;
  }

  public double getMembershipFee() {
    return membershipFee;
  }

  public List<Book> getBooks() {
    return new ArrayList<>(books);
  }

  public List<String> getMembers() {
    return new ArrayList<>(members);
  }

  // Setters for mutable fields
  public void setTotalCapacity(int totalCapacity) {
    this.totalCapacity = totalCapacity;
  }

  public void setMembershipFee(double membershipFee) {
    this.membershipFee = membershipFee;
  }
}
