package library;

import com.library.model.Book;
import com.library.model.User;
import com.library.repository.LibraryRepository;
import com.library.service.LibraryServices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private LibraryRepository repo;
    private LibraryServices service;

    @BeforeEach
    void setUp() {
        repo = new LibraryRepository();
        service = new LibraryServices(repo);
    }

    @Test
    void newBookShouldBeAvailable() {
        Book book =
                new Book(
                        "101",
                        "Clean Code",
                        "Robert Martin",
                        "Programming",
                        true
                );

        assertTrue(book.isAvailable());
    }


    @Test
    void addBookShouldStoreBook() {
        service.addBook(
                new Book(
                        "101",
                        "Clean Code",
                        "Robert Martin",
                        "Programming",
                        true
                )
        );

        assertEquals( 1, service.getAllBooks().size()
        );
    }


    @Test
    void findBookByIsbnShouldReturnBook() {

        Book book =
                new Book(
                        "101",
                        "Clean Code",
                        "Robert Martin",
                        "Programming",
                        true
                );

        service.addBook(book);

        Book result = service.searchByIsbn("101");

        assertNotNull(result);
    }


    @Test
    void invalidIsbnShouldReturnNull() {

        Book result = service.searchByIsbn("999");

        assertNull(result); //
    }


    @Test
    void searchShouldFindBookByTitle() {

        service.addBook(
                new Book(
                        "001",
                        "Clean Code",
                        "Robert Martin",
                        "Programming",
                        true
                )
        );

        List<Book> results = service.searchByTitle("Clean");

        assertEquals( 1, results.size());
    }


    @Test
    void searchShouldFindBookByAuthor() {

        service.addBook(
                new Book(
                        "001",
                        "Clean Code",
                        "Robert Martin",
                        "Programming",
                        false
                )
        );

        List<Book> results = service.searchByAuthor("Robert");

        assertFalse(results.isEmpty());
    }

    @Test
    void searchShouldFindBookByCategory() {

        service.addBook(
                new Book(
                        "001",
                        "Clean Code",
                        "Robert Martin",
                        "Programming",
                        true
                )
        );

        List<Book> results = service.searchByCategory("Programming");

        assertEquals(1,results.size());
    }


    @Test
    void searchShouldIgnoreCase() {

        service.addBook(
                new Book(
                        "001",
                        "Clean Code",
                        "Robert Martin",
                        "Programming",
                        true
                )
        );

        List<Book> results = service.searchByTitle("CLEAN");

        assertFalse(results.isEmpty());
    }


    @Test
    void searchShouldReturnEmptyListWhenNotFound() {

        service.addBook(
                new Book(
                        "001",
                        "Clean Code",
                        "Robert Martin",
                        "Programming",
                        false
                )
        );

        List<Book> results = service.searchByTitle("Cleaned");

        assertTrue(results.isEmpty());
    }


    @Test
    void userShouldBeAbleToBorrowBook() {

        User user =
                new User(
                        1,
                        "John",
                        0
                );

        Book book =
                new Book(
                        "001",
                        "Clean Code",
                        "Robert Martin",
                        "Programming",
                        true
                );

        service.addUser(user);
        service.addBook(book);

        boolean result =
                service.borrowBook(
                        1,
                        "001"
                );

        assertTrue(result);
    }


    @Test
    void borrowedBookShouldBecomeUnavailable() {

        User user =
                new User(
                        1,
                        "John",
                        3
                );

        Book book =
                new Book(
                        "001",
                        "Clean Code",
                        "Robert Martin",
                        "Programming",
                        false
                );

        service.addUser(user);
        service.addBook(book);

        service.borrowBook(
                1,
                "001"
        );

        assertFalse(book.isAvailable());
    }


    @Test
    void unavailableBookCannotBeBorrowedTwice() {

        User user1 =
                new User(1, "John",0);

        User user2 =
                new User(2, "Mary",0);

        Book book =
                new Book(
                        "001",
                        "Clean Code",
                        "Robert Martin",
                        "Programming",
                        true
                );

        service.addUser(user1);
        service.addUser(user2);
        service.addBook(book);

        service.borrowBook(
                1,
                "001"
        );

        boolean secondBorrow =
                service.borrowBook(
                        2,
                        "001"
                );

        assertFalse(secondBorrow);
    }

    @Test
    void returnedBookShouldBecomeAvailable() {

        User user =
                new User(1, "John",2);

        Book book =
                new Book(
                        "001",
                        "Clean Code",
                        "Robert Martin",
                        "Programming",
                        false
                );

        service.addUser(user);
        service.addBook(book);

        service.borrowBook(
                1,
                "001"
        );

        service.returnBook(
                1,
                "001"
        );

        assertTrue(
                book.isAvailable()
        );
    }

    @Test
    void userCannotBorrowMoreThanThreeBooks() {

        User user =
                new User(1, "John",1);

        service.addUser(user);

        for (int i = 1; i <= 4; i++) {

            service.addBook(
                    new Book(
                            "00" + i,
                            "Book " + i,
                            "Author",
                            "Category",
                            true
                    )
            );
        }

        assertTrue(
                service.borrowBook(
                        1,
                        "001"
                )
        );

        assertTrue(
                service.borrowBook(
                        1,
                        "002"
                )
        );

        assertTrue(
                service.borrowBook(
                        1,
                        "003"
                )
        );

        assertFalse(
                service.borrowBook(
                        1,
                        "004"
                )
        );
    }

    @Test
    void userCannotReturnBookTheyDidNotBorrow() {

        User user =
                new User(1, "John",1);

        Book book =
                new Book(
                        "001",
                        "Clean Code",
                        "Robert Martin",
                        "Programming",
                        false
                );

        service.addUser(user);
        service.addBook(book);

        boolean result =
                service.returnBook(
                        1,
                        "001"
                );

        assertFalse(result);
    }

}