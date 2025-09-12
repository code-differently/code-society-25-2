package com.codedifferently.lesson16.library;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LibraryTest {

  private Library library;
  private Book book1;
  private Book book2;
  private Book book3;

  @BeforeEach
  void setUp() {
    library = new Library("Central Library", 3, 0.50);
    book1 =
        new Book(
            "978-0-545-01022-1",
            "Harry Potter and the Philosopher's Stone",
            "J.K. Rowling",
            BookGenre.FANTASY);
    book2 = new Book("978-0-06-112008-4", "To Kill a Mockingbird", "Harper Lee", BookGenre.FICTION);
    book3 = new Book("978-0-385-33312-0", "The Da Vinci Code", "Dan Brown", BookGenre.MYSTERY);
  }

  @Test
  public void testLibraryConstructor() {
    assertThat(library.getLibraryName()).isEqualTo("Central Library");
    assertThat(library.getMaxBooksPerMember()).isEqualTo(3);
    assertThat(library.getLateFeePerDay()).isEqualTo(0.50);
    assertThat(library.isOpen()).isTrue();
    assertThat(library.getTotalBooksCount()).isEqualTo(0);
  }

  @Test
  public void testAddBook() {
    library.addBook(book1);
    library.addBook(book2);

    assertThat(library.getTotalBooksCount()).isEqualTo(2);
    assertThat(library.getAllBooks()).containsExactly(book1, book2);
  }

  @Test
  public void testAddDuplicateBook_shouldNotAddDuplicate() {
    library.addBook(book1);
    library.addBook(book1); // Try to add the same book again

    assertThat(library.getTotalBooksCount()).isEqualTo(1);
  }

  @Test
  public void testFindBooksByGenre() {
    Book fantasyBook2 =
        new Book("978-0-547-92822-7", "The Hobbit", "J.R.R. Tolkien", BookGenre.FANTASY);
    library.addBook(book1); // Fantasy
    library.addBook(book2); // Fiction
    library.addBook(book3); // Mystery
    library.addBook(fantasyBook2); // Fantasy

    List<Book> fantasyBooks = library.findBooksByGenre(BookGenre.FANTASY);
    List<Book> fictionBooks = library.findBooksByGenre(BookGenre.FICTION);
    List<Book> scienceBooks = library.findBooksByGenre(BookGenre.SCIENCE);

    assertThat(fantasyBooks).hasSize(2);
    assertThat(fantasyBooks).containsExactly(book1, fantasyBook2);
    assertThat(fictionBooks).hasSize(1);
    assertThat(fictionBooks).containsExactly(book2);
    assertThat(scienceBooks).isEmpty();
  }

  @Test
  public void testBorrowBook_success() throws BookNotFoundException {
    library.addBook(book1);
    String memberId = "MEMBER123";

    library.borrowBook(book1.getIsbn(), memberId);

    assertThat(book1.isAvailable()).isFalse();
  }

  @Test
  public void testBorrowBook_whenBookNotFound_shouldThrowException() {
    assertThatThrownBy(() -> library.borrowBook("INVALID-ISBN", "MEMBER123"))
        .isInstanceOf(BookNotFoundException.class)
        .hasMessage("Book with ISBN 'INVALID-ISBN' was not found in the library.");
  }

  @Test
  public void testBorrowBook_whenLibraryClosed_shouldThrowException() {
    library.addBook(book1);
    library.setOpen(false);

    assertThatThrownBy(() -> library.borrowBook(book1.getIsbn(), "MEMBER123"))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("Library is currently closed");
  }

  @Test
  public void testBorrowBook_whenBookNotAvailable_shouldThrowException()
      throws BookNotFoundException {
    library.addBook(book1);
    library.borrowBook(book1.getIsbn(), "MEMBER123"); // First borrowing

    assertThatThrownBy(() -> library.borrowBook(book1.getIsbn(), "MEMBER456"))
        .isInstanceOf(BookNotFoundException.class)
        .hasMessage("Book is currently not available");
  }

  @Test
  public void testReturnBook_success() throws BookNotFoundException {
    library.addBook(book1);
    library.borrowBook(book1.getIsbn(), "MEMBER123");

    double lateFee = library.returnBook(book1.getIsbn());

    assertThat(book1.isAvailable()).isTrue();
    assertThat(lateFee).isEqualTo(0.0); // No late fee for immediate return
  }

  @Test
  public void testReturnBook_whenBookNotBorrowed_shouldThrowException() {
    library.addBook(book1);

    assertThatThrownBy(() -> library.returnBook(book1.getIsbn()))
        .isInstanceOf(BookNotFoundException.class)
        .hasMessage("Book was not borrowed from this library");
  }

  @Test
  public void testGetLibraryStatistics() {
    library.addBook(book1); // Fantasy
    library.addBook(book2); // Fiction
    library.addBook(book3); // Mystery

    Book anotherFantasyBook =
        new Book("978-0-547-92822-7", "The Hobbit", "J.R.R. Tolkien", BookGenre.FANTASY);
    library.addBook(anotherFantasyBook); // Another Fantasy book

    try {
      library.borrowBook(book1.getIsbn(), "MEMBER123");
    } catch (BookNotFoundException e) {
      // This shouldn't happen in this test
    }

    String statistics = library.getLibraryStatistics();

    assertThat(statistics).contains("Central Library");
    assertThat(statistics).contains("Total Books: 4");
    assertThat(statistics).contains("Available: 3");
    assertThat(statistics).contains("Borrowed: 1");
    assertThat(statistics).contains("Most Popular Genre: Fantasy"); // 2 fantasy books
    assertThat(library.getMostPopularGenre()).isEqualTo(BookGenre.FANTASY);
  }

  @Test
  public void testBookGenreEnum() {
    assertThat(BookGenre.FICTION.getDisplayName()).isEqualTo("Fiction");
    assertThat(BookGenre.SCIENCE_FICTION.toString()).isEqualTo("Science Fiction");
    assertThat(BookGenre.values())
        .contains(BookGenre.FICTION, BookGenre.FANTASY, BookGenre.MYSTERY);
  }

  @Test
  public void testBookEquality() {
    Book book1Copy =
        new Book("978-0-545-01022-1", "Different Title", "Different Author", BookGenre.SCIENCE);

    assertThat(book1).isEqualTo(book1Copy); // Same ISBN
    assertThat(book1.hashCode()).isEqualTo(book1Copy.hashCode());
    assertThat(book1).isNotEqualTo(book2); // Different ISBN
  }

  @Test
  public void testCustomException() {
    BookNotFoundException exception = new BookNotFoundException("TEST-ISBN");

    assertThat(exception.getIsbn()).isEqualTo("TEST-ISBN");
    assertThat(exception.getMessage()).contains("TEST-ISBN");
    assertThat(exception.getMessage()).contains("not found");

    BookNotFoundException customException =
        new BookNotFoundException("TEST-ISBN-2", "Custom message");
    assertThat(customException.getIsbn()).isEqualTo("TEST-ISBN-2");
    assertThat(customException.getMessage()).isEqualTo("Custom message");
  }
}
