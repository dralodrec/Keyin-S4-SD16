package com.library.repository;

import java.util.ArrayList;
import java.util.List;
import com.library.model.Book;
import com.library.model.User;

public class LibraryRepository {
    private final List<Book> books = new ArrayList<>();
    private final List<User> users = new ArrayList<>();

    public LibraryRepository() {
        // Initialize the repository with some sample data
        books.add(new Book("978-3-16-148410-0", "Book 1", "Author 1", "Fiction", false));
        books.add(new Book("978-1-23-456789-7", "Book 2", "Author 2", "Non-Fiction", false));
        books.add(new Book("978-0-12-345678-9", "Book 3", "Author 3", "Science", false));

        users.add(new User(1, "User 1", new ArrayList<>()));
        users.add(new User(2, "User 2", new ArrayList<>()));
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null; // Book not found
    }

    public User findUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null; // User not found
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<User> getUsers() {
        return users;
    }
}
