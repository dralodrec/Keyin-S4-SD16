package com.library.model;

import java.util.List;

public class User {
    private final int id;
    private final String name;

    private final List<Book> borrowedBooks;

    //private static final int MAX_BOOKS = 3;

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
}
