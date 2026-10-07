package com.library.service;

import com.library.model.Book;
import com.library.model.User;
import com.library.repository.LibraryRepository;

import java.util.ArrayList;
import java.util.List;

public class LibraryServices {

    private final LibraryRepository repo;

    public LibraryServices(LibraryRepository repo) {
        this.repo = repo;
    }

    public void addBook(Book book) {
        repo.addBook(book);
    }

    public void addUser(User user) {
        repo.addUser(user);
    }

    public List<Book> searchByTitle(String title) {

        List<Book> results = new ArrayList<>();

        String searchTerm = title.trim().toLowerCase();

        for (Book book : repo.getAllBooks()) {

            if (book.getTitle()
                    .toLowerCase()
                    .contains(searchTerm)) {

                results.add(book);
            }
        }

        return results;
    }

    public List<Book> searchByAuthor(String author) {

        List<Book> results = new ArrayList<>();

        String searchTerm = author.trim().toLowerCase();

        for (Book book : repo.getAllBooks()) {

            if (book.getAuthor()
                    .toLowerCase()
                    .contains(searchTerm)) {

                results.add(book);
            }
        }

        return results;
    }

    public Book searchByIsbn(String isbn) {
        return repo.findBookByIsbn(isbn.trim());
    }

    public List<Book> searchByCategory(String category) {

        List<Book> results = new ArrayList<>();

        String searchTerm = category.trim().toLowerCase();

        for (Book book : repo.getAllBooks()) {

            if (book.getCategory()
                    .toLowerCase()
                    .contains(searchTerm)) {

                results.add(book);
            }
        }

        return results;
    }

    public List<Book> searchBooks(String keyword) {

        List<Book> results = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return results;
        }

        String searchTerm = keyword.trim().toLowerCase();

        for (Book book : repo.getAllBooks()) {

            if (book.getTitle().toLowerCase().contains(searchTerm)
                    || book.getAuthor().toLowerCase().contains(searchTerm)
                    || book.getCategory().toLowerCase().contains(searchTerm)
                    || book.getIsbn().toLowerCase().contains(searchTerm)) {

                results.add(book);
            }
        }

        return results;
    }

    public List<Book> getAllBooks() {
        return repo.getAllBooks();
    }

    public boolean borrowBook(int userId, String isbn) {

        User user = repo.findUserById(userId);
        Book book = repo.findBookByIsbn(isbn);

        if (user == null || book == null) {
            return false;
        }

        if (!book.isAvailable()) {
            return false;
        }

        if (!user.canBorrowBook()) {
            return false;
        }

        user.borrowBook(book);
        book.borrow();

        return true;
    }

    public boolean returnBook(int userId, String isbn) {

        User user = repo.findUserById(userId);
        Book book = repo.findBookByIsbn(isbn);

        if (user == null || book == null) {
            return false;
        }

        if (!user.hasBorrowed(book)) {
            return false;
        }

        user.returnBook(book);
        book.returnBook();

        return true;
    }
}