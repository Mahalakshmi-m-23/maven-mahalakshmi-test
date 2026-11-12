package com.example.maven_mahalakshmi;

import java.util.HashMap;
import java.util.Map;

public class library {

    public static class Book {
        private final String bookId;
        private final String title;
        private boolean available = true;

        public Book(String bookId, String title) {
            this.bookId = bookId;
            this.title = title;
        }

        public String getBookId() { return bookId; }
        public String getTitle() { return title; }
        public boolean isAvailable() { return available; }
        public void setAvailable(boolean available) { this.available = available; }
    }

    private final Map<String, Book> books = new HashMap<>();

    public void addBook(String bookId, String title) {
        if (books.containsKey(bookId)) {
            throw new IllegalArgumentException("Duplicate Book ID: " + bookId);
        }
        books.put(bookId, new Book(bookId, title));
    }

    public Book searchBook(String bookId) {
        return books.get(bookId);
    }

    public boolean issueBook(String bookId) {
        Book book = books.get(bookId);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            return true;
        }
        return false;
    }

    public boolean returnBook(String bookId) {
        Book book = books.get(bookId);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            return true;
        }
        return false;
    }
}