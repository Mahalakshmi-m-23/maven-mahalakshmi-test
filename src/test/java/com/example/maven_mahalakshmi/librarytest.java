package com.example.maven_mahalakshmi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class librarytest {

    private library lib;

    @BeforeEach
    void setUp() {
        lib = new library();
    }

    @Test
    void testAddAndSearchBook() {
        lib.addBook("B01", "Clean Code");

        library.Book found = lib.searchBook("B01");
        assertNotNull(found, "Book should be found");
        assertEquals("Clean Code", found.getTitle());
        assertTrue(found.isAvailable(), "New book should be available");
        assertNull(lib.searchBook("B99"), "Unknown ID should return null");
    }

    @Test
    void testDuplicateBookId() {
        lib.addBook("B01", "Clean Code");

        assertThrows(IllegalArgumentException.class, () -> {
            lib.addBook("B01", "Effective Java");
        }, "Adding a duplicate book ID should throw an exception");
    }

    @Test
    void testIssueBook() {
        lib.addBook("B02", "Design Patterns");

        // Issue an available book
        assertTrue(lib.issueBook("B02"), "Issuing available book should return true");
        assertFalse(lib.searchBook("B02").isAvailable(), "Book should now be unavailable");

        // Issue an already-issued book
        assertFalse(lib.issueBook("B02"), "Issuing already-issued book should return false");
    }

    @Test
    void testReturnBook() {
        lib.addBook("B03", "Refactoring");
        lib.issueBook("B03");

        // Return the issued book
        assertTrue(lib.returnBook("B03"), "Returning issued book should return true");
        assertTrue(lib.searchBook("B03").isAvailable(), "Book should be available again");

        // Return a book that is already available
        assertFalse(lib.returnBook("B03"), "Returning an available book should return false");
    }
}