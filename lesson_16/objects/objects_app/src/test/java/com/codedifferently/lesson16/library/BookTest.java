package com.codedifferently.lesson16.library;

import org.junit.jupiter.api.Test;

import main.java.com.codedifferently.lesson16.library.Book;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    
    private Book testBook;
    
    @BeforeEach
    public void setUp() {
        // Create a test book before each test
        testBook = new Book("The Great Gatsby", "F. Scott Fitzgerald", 180, 12.99);
    }
    
    @Test
    public void testConstructor() {
        // Test 1: Constructor sets values correctly
        assertEquals("The Great Gatsby", testBook.getTitle());
        assertEquals("F. Scott Fitzgerald", testBook.getAuthor());
        assertEquals(180, testBook.getPages());
        assertEquals(12.99, testBook.getPrice());
        assertTrue(testBook.isAvailable());
    }
    
    @Test
    public void testAvailabilityInfo() {
        // Test 2: Conditional expression works correctly
        String info = testBook.getAvailabilityInfo();
        assertEquals("This book is available for checkout", info);
    }
    @Test
    public void testAddGenre() throws BookException {
        // Test 3: Collection usage works correctly
        testBook.addGenre("Fiction");
        testBook.addGenre("Classic");
        
        assertEquals(2, testBook.getGenres().size());
        assertTrue(testBook.getGenres().contains("Fiction"));
        assertTrue(testBook.getGenres().contains("Classic"));
    }
    
    @Test
    public void testGenreDisplay() throws BookException {
        // Test 4: Loop functionality works correctly
        testBook.addGenre("Fiction");
        testBook.addGenre("Drama");
        
        String display = testBook.getGenreDisplay();
        assertEquals("Genres: Fiction, Drama", display);
    }
    
    @Test
    public void testBookException() {
        // Test 5: Custom exception works correctly
        Exception exception = assertThrows(BookException.class, () -> {
            testBook.addGenre("");
        });
        
        assertEquals("Genre cannot be null or empty", exception.getMessage());
    }
}

