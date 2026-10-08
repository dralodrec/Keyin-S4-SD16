package com.library;

import com.library.model.Book;
import com.library.model.User;
import com.library.repository.LibraryRepository;
import com.library.service.LibraryServices;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        LibraryRepository repo = new LibraryRepository();
        LibraryServices service = new LibraryServices(repo);

        boolean running = true;

        while (running) {

            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Search Catalog");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Show All Books");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    addBook(scanner, service);
                    break;

                case "2":
                    addUser(scanner, service);
                    break;

                case "3":
                    searchMenu(scanner, service);
                    break;

                case "4":
                    borrowBook(scanner, service);
                    break;

                case "5":
                    returnBook(scanner, service);
                    break;

                case "6":
                    displayBooks(service.getAllBooks());
                    break;

                case "7":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    private static void addBook(Scanner scanner, LibraryServices service) {

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        Book book = new Book(
                isbn,
                title,
                author,
                category,
                true
        );

        service.addBook(book);

        System.out.println("Book added successfully.");
    }

    private static void addUser(Scanner scanner, LibraryServices service) {

        System.out.print("User ID: ");

        try {

            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("User name: ");
            String name = scanner.nextLine();

            service.addUser(new User(id, name, 0)
            );

            System.out.println("User added successfully.");

        } catch (NumberFormatException e) {

            System.out.println(
                    "User ID must be a number."
            );
        }
    }

    private static void searchMenu(Scanner scanner, LibraryServices service) {

        boolean searching = true;

        while (searching) {

            System.out.println("\n=== Search Catalog ===");
            System.out.println("1. Search by Title");
            System.out.println("2. Search by Author");
            System.out.println("3. Search by ISBN");
            System.out.println("4. Search by Category");
            System.out.println("5. Show All Books");
            System.out.println("6. Back");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":

                    System.out.print("Enter title: ");

                    displayBooks(
                            service.searchByTitle(
                                    scanner.nextLine()
                            )
                    );

                    break;

                case "2":

                    System.out.print("Enter author: ");

                    displayBooks(
                            service.searchByAuthor(
                                    scanner.nextLine()
                            )
                    );

                    break;

                case "3":

                    System.out.print("Enter ISBN: ");

                    Book book =
                            service.searchByIsbn(
                                    scanner.nextLine()
                            );

                    if (book == null) {
                        System.out.println(
                                "Book not found."
                        );
                    } else {
                        System.out.println(book);
                    }

                    break;

                case "4":

                    System.out.print(
                            "Enter category: "
                    );

                    displayBooks(
                            service.searchByCategory(
                                    scanner.nextLine()
                            )
                    );

                    break;

                case "5":
                    displayBooks(
                            service.getAllBooks()
                    );
                    break;

                case "6":
                    searching = false;
                    break;

                default:
                    System.out.println(
                            "Invalid option."
                    );
            }
        }
    }

    private static void borrowBook(Scanner scanner, LibraryServices service) {

        try {

            System.out.print("User ID: ");
            int userId =
                    Integer.parseInt(
                            scanner.nextLine()
                    );

            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();

            boolean success =
                    service.borrowBook(
                            userId,
                            isbn
                    );

            if (success) {
                System.out.println(
                        "Book borrowed successfully."
                );
            } else {
                System.out.println(
                        "Unable to borrow book."
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "User ID must be a number."
            );
        }
    }

    private static void returnBook(Scanner scanner, LibraryServices service) {

        try {

            System.out.print("User ID: ");
            int userId =
                    Integer.parseInt(
                            scanner.nextLine()
                    );

            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();

            boolean success =
                    service.returnBook(
                            userId,
                            isbn
                    );

            if (success) {
                System.out.println(
                        "Book returned successfully."
                );
            } else {
                System.out.println(
                        "Unable to return book."
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "User ID must be a number."
            );
        }
    }

    private static void displayBooks(
            List<Book> books) {

        if (books.isEmpty()) {

            System.out.println(
                    "No books found."
            );

            return;
        }

        System.out.println("\nBooks:");

        for (Book book : books) {
            System.out.println(book);
        }
    }
}