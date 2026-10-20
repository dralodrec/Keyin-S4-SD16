# LMS (Library Management System) 
<br>

## Features
    The application supports:
    - Adding books
    - Adding library users
    - Searching books by title
    - Searching books by author
    - Searching books by ISBN
    - Searching books by category
    - Viewing all books
    - Borrowing books
    - Returning books
    - Checking book availability
    - Enforcing a borrowing limit
    - Preventing unavailable books from being borrowed twice
<br>

## Architecture
    The project uses a simple layered architecture:
    Main
    ↓
    LibraryService
    ↓
    LibraryRepository
    ↓
    Book / User
<br>

## Project Structure
    LMS/
    ├── .github/
    │   └── workflows/
    │       └── maven.yml
    │
    ├── src/
    │   ├── main/
    │   │   └── java/
    │   │       └── com/library/
    │   │           ├── Main.java
    │   │           ├── model/
    │   │           │   ├── Book.java
    │   │           │   └── User.java
    │   │           ├── repository/
    │   │           │   └── LibraryRepository.java
    │   │           └── service/
    │   │               └── LibraryService.java
    │   │
    │   └── test/
    │       └── java/
    │           └── com/library/
    │               └── LibraryTest.java
    │
    ├── pom.xml
    ├── .gitignore
    └── README.md
<br>

## Clean Code Practices
    This is probably the most important section to improve because your instructor specifically mentioned:
    Missing screenshots showing clean code in your work.

    You should include at least three examples from your own source code and place a screenshot under each example.
    Clean Code Example 1 — Single Responsibility
    Each class has one main responsibility.

        For example:
        Main
        → handles user interaction

        LibraryService
        → handles application rules

        LibraryRepository
        → handles data storage

        Book / User
        → represent application data

Instead of placing all application logic inside Main.java, the logic was divided into separate classes.

    For example, Main calls:
    boolean success = service.borrowBook(userId, isbn);

but the actual borrowing rules are handled inside LibraryService.
This improves readability, maintainability, and testability.

    Screenshot to be added:


### Clean Code Example 2 — Descriptive Method Names
The project uses method names that describe their purpose.

    Examples:
    searchByTitle()
    searchByAuthor()
    findBookByIsbn()
    borrowBook()
    returnBook()
    canBorrowBook()
    hasBorrowed()

    For example:
    public boolean canBorrowBook() {
        return borrowedBooks.size() < MAX_BOOKS;
    }

The method name clearly communicates the rule being checked.
This is better than vague names such as:
check()
process()
doThing()

because another developer can understand the intent without reading the entire method.

    Screenshot to be added:


### Clean Code Example 3 — Avoiding Magic Numbers
The borrowing limit is stored as a constant:

    private static final int MAX_BOOKS = 3;

instead of writing:

    borrowedBooks.size() < 3

throughout the program.
The method then becomes:

    public boolean canBorrowBook() {
        return borrowedBooks.size() < MAX_BOOKS;
    }

This makes the business rule easier to understand and easier to change.
If the borrowing limit changes later, only one value needs to be updated.

    Screenshot to be added:


### Additional Clean Coding Improvement
Here is another good clean-code idea you can add if you want stronger evidence.

Constructor Injection

    LibraryService receives its repository through its constructor:
    private final LibraryRepository repo;

    public LibraryService(LibraryRepository repo) {
        this.repo = repo;
    }

This is cleaner than creating the repository directly inside the service:

    private LibraryRepository repo =
            new LibraryRepository();

Constructor injection makes dependencies explicit.
It also makes unit testing easier because the test can create a new repository and pass it to the service:

    repo = new LibraryRepository();
    service = new LibraryService(repo);

This also helped fix the earlier NullPointerException caused by an uninitialized repository.

    Screenshot to be added:
<br>

## How the Application Works
When the program starts, the user is presented with a menu.

    Example:
    === Library Management System ===

    1. Add Book
    2. Add User
    3. Search Catalog
    4. Borrow Book
    5. Return Book
    6. Show All Books
    7. Exit

When a user chooses an operation, Main.java collects the required input and calls the appropriate method in LibraryService.

    For example:
    service.borrowBook(userId, isbn);

The service then retrieves the required information from the repository.

    The borrowing process follows these rules:
    Find user
    ↓
    Find book
    ↓
    Check book availability
    ↓
    Check borrowing limit
    ↓
    Borrow book

If any rule fails, the operation returns false.
<br>

## Unit Test
    | Test                                          | What it verifies                          | Scenario |

    | `newBookShouldBeAvailable()`                  | New books start as available              | Passed   |
    | `addBookShouldStoreBook()`                    | Repository/service stores a book          | Passed   |
    | `findBookByIsbnShouldReturnBook()`            | Existing ISBN can be found                | Passed   |
    | `invalidIsbnShouldReturnNull()`               | Unknown ISBN is handled safely            | Failed   |
    | `searchShouldFindBookByTitle()`               | Search by title works                     | Passed   |
    | `searchShouldFindBookByAuthor()`              | Search by author works                    | Passed   |
    | `searchShouldFindBookByCategory()`            | Search by category works                  | Passed   |
    | `searchShouldIgnoreCase()`                    | Search is case-insensitive                | Passed   |
    | `searchShouldReturnEmptyListWhenNotFound()`   | Missing search result is handled          | Failed   |
    | `userShouldBeAbleToBorrowBook()`              | Valid user can borrow available book      | Passed   |
    | `borrowedBookShouldBecomeUnavailable()`       | Availability changes after borrow         | Passed   |
    | `unavailableBookCannotBeBorrowedTwice()`      | Same book cannot be borrowed twice        | Failed   |
    | `returnedBookShouldBecomeAvailable()`         | Return restores availability              | Passed   |
    | `userCannotBorrowMoreThanThreeBooks()`        | Borrowing limit is enforced               | Failed   |
    | `userCannotReturnBookTheyDidNotBorrow()`      | Invalid return is rejected                | Failed   |

