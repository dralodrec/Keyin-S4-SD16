package com.library.service;

import java.util.Collection;
import java.util.List;

import com.library.model.Book;
import com.library.model.User;

public class LibraryServices {

    public List<Book> searchByTitle(String title, Collection<Book> books) {
    return books.stream()
            .filter(book ->
                book.getTitle()
                    .toLowerCase()
                    .contains(title.toLowerCase()))
            .toList();
    }

    public List<Book> searchByAuthor(String author, Collection<Book> books) {
        return books.stream()
                .filter(book ->
                    book.getAuthor()
                        .toLowerCase()
                        .contains(author.toLowerCase()))
                .toList();
    }

    public boolean borrowBook(User user, Book book) {

        if (!book.isAvailable()) {
            return false;
        }

        if (!user.canBorrowBook()) {
            return false;
        }

        book.borrow();
        user.borrowBook(book);

        return true;
    }

    public boolean returnBook(User user, Book book) {

        if (!user.hasBorrowed(book)) {
            return false;
        }

        user.returnBook(book);
        book.returnBook();

        return true;
    }
}
