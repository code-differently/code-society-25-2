package com.codedifferently.lesson16.thusser;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** Test class for Library */
public class LibraryTest {

  private Library library;
  private Book book1;
  private Book book2;
  private Book book3;

  @BeforeEach
  void setUp() {
    library = new Library("Central Library", "123 Main St", 1000, LocalDate.of(1950, 1, 1), 25.99);

    book1 =
        new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0-7432-7356-5", BookGenre.FICTION);
    book2 = new Book("Dune", "Frank Herbert", "978-0-441-17271-9", BookGenre.SCIENCE_FICTION);
    book3 = new Book("The Hobbit", "J.R.R. Tolkien", "978-0-547-92822-7", BookGenre.FANTASY);
  }

  @Test
  @DisplayName("Test library constructor and getters")
  void testLibraryConstructorAndGetters() {
    assertEquals("Central Library", library.getName());
    assertEquals("123 Main St", library.getAddress());
    assertEquals(1000, library.getTotalCapacity());
    assertEquals(LocalDate.of(1950, 1, 1), library.getEstablishedDate());
    assertEquals(25.99, library.getMembershipFee(), 0.01);
    assertTrue(library.getBooks().isEmpty());
    assertTrue(library.getMembers().isEmpty());
  }

  @Test
  @DisplayName("Test adding books to library")
  void testAddBook() throws LibraryException {
    // Test successful book addition
    library.addBook(book1);
    assertEquals(1, library.getBooks().size());
    assertTrue(library.getBooks().contains(book1));

    // Test adding duplicate book throws exception
    LibraryException exception =
        assertThrows(
            LibraryException.class,
            () -> {
              library.addBook(book1);
            });
    assertTrue(exception.getMessage().contains("already exists"));

    // Test adding null book throws exception
    assertThrows(
        LibraryException.class,
        () -> {
          library.addBook(null);
        });
  }

  @Test
  @DisplayName("Test finding books by genre")
  void testFindBooksByGenre() throws LibraryException {
    // Add books of different genres
    library.addBook(book1); // FICTION
    library.addBook(book2); // SCIENCE_FICTION
    library.addBook(book3); // FANTASY

    // Test finding fiction books
    List<Book> fictionBooks = library.findBooksByGenre(BookGenre.FICTION);
    assertEquals(1, fictionBooks.size());
    assertEquals(book1, fictionBooks.get(0));

    // Test finding books of genre that doesn't exist
    List<Book> mysteryBooks = library.findBooksByGenre(BookGenre.MYSTERY);
    assertTrue(mysteryBooks.isEmpty());

    // Test null genre throws exception
    assertThrows(
        LibraryException.class,
        () -> {
          library.findBooksByGenre(null);
        });
  }

  @Test
  @DisplayName("Test member management and revenue calculation")
  void testMemberManagementAndRevenue() throws LibraryException {
    // Test adding members
    library.addMember("John Doe");
    library.addMember("Jane Smith");
    assertEquals(2, library.getMembers().size());

    // Test calculating revenue with small member base (no discount)
    double expectedRevenue = 2 * 25.99 * 12; // 2 members * fee * 12 months
    assertEquals(expectedRevenue, library.calculateAnnualRevenue(), 0.01);

    // Test adding duplicate member throws exception
    assertThrows(
        LibraryException.class,
        () -> {
          library.addMember("John Doe");
        });

    // Test adding null/empty member throws exception
    assertThrows(
        LibraryException.class,
        () -> {
          library.addMember(null);
        });
    assertThrows(
        LibraryException.class,
        () -> {
          library.addMember("");
        });
    assertThrows(
        LibraryException.class,
        () -> {
          library.addMember("   ");
        });
  }

  @Test
  @DisplayName("Test library capacity and statistics")
  void testLibraryCapacityAndStatistics() throws LibraryException {
    // Test initial capacity check
    assertTrue(library.canAddMoreBooks());

    // Add some books and members
    library.addBook(book1);
    library.addBook(book2);
    library.addMember("Alice Johnson");

    // Test capacity is still available
    assertTrue(library.canAddMoreBooks());

    // Test statistics generation
    String stats = library.getLibraryStatistics();
    assertTrue(stats.contains("Central Library"));
    assertTrue(stats.contains("Total Books: 2"));
    assertTrue(stats.contains("Available: 2"));
    assertTrue(stats.contains("Members: 1"));

    // Test with checked out book
    book1.setAvailable(false);
    String updatedStats = library.getLibraryStatistics();
    assertTrue(updatedStats.contains("Available: 1"));
    assertTrue(updatedStats.contains("Checked Out: 1"));
  }

  @Test
  @DisplayName("Test revenue calculation with large member base (discount)")
  void testRevenueCalculationWithDiscount() throws LibraryException {
    // Add more than 100 members to trigger discount
    for (int i = 1; i <= 105; i++) {
      library.addMember("Member" + i);
    }

    assertEquals(105, library.getMembers().size());

    // Calculate expected revenue with 10% discount
    double baseRevenue = 105 * 25.99 * 12;
    double expectedRevenue = baseRevenue * 0.9; // 10% discount

    assertEquals(expectedRevenue, library.calculateAnnualRevenue(), 0.01);
  }

  @Test
  @DisplayName("Test setters for mutable fields")
  void testSetters() {
    library.setTotalCapacity(2000);
    assertEquals(2000, library.getTotalCapacity());

    library.setMembershipFee(29.99);
    assertEquals(29.99, library.getMembershipFee(), 0.01);
  }
}
