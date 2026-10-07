package com.library.model;

import java.util.List;

public class User {
    private final int id;
    private final String name;

    private final List<Book> borrowedBooks;

    //private static final int MAX_BOOKS = 3;

    //public record User(int id, String name, List<Book> borrowedBooks) {

    public User(int id, String name, List<Book> borrowedBooks) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = borrowedBooks;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public boolean canBorrowBook() {
        return borrowedBooks.size() < 3;
    }
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }
    public boolean hasBorrowed(Book book) {
        return borrowedBooks.contains(book);
    }
    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}
