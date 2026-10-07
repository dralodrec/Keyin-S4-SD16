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

        assertEquals(
                1,
                service.getAllBooks().size()
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

}