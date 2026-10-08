# Library Management System
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


## Features
  List the main functions of the application, such as:
  - Add books
  - Add users
  - Search books by title, author, ISBN, or category
  - Borrow books
  - Return books
  - Check book availability
  - Enforce borrowing limits


## Architecture
  Main
    ↓
  LibraryService
    ↓
  LibraryRepository
    ↓
  Book / User


## Project Structure
  src/main/java/com/library/
    - Main.java
    - model/
    - repository/
    - service/

  src/test/java/com/library/
    - LibraryTest.java


## How to Run
  - mvn clean test
  - mvn clean package


## Documentation:
1. Explain how your code meets clean code practices by using at least 3 examples of your own code. Screenshots should be used
  clean code practices by separating different responsibilities into different classes and packages. The application is organized   into model, repository, and service packages while Main.java is responsible for the menu and user interaction.
   
2. Explain your project. What it does, how it works. Explain the test cases you used
  This project is a console-based Library Management System written in Java. This allows a user to manage books and library users. functionality includes adding books, adding users, searching the catalog, borrowing books, returning books and displaying books, checking book availability, and enforcing a borrowing limit.

3. Outline the needed dependencies. Where did you get them from?
  JUnit 5 provides a testing framework while Maven framework adds dependency and build automation. Maven Surefire Plugin allows to locate and execute the JUnit testing.

4. If you had any problems the QAP please explain what happened.
  Encountered a lot of problem that may connect to users and repository. When using the services without providing it with a    LibraryRepository, tested a couple of multiple constructor injection that ensures the services has access to a valid repository.
  
  private final LibraryRepository repo;
  
  public LibraryService(LibraryRepository repo) {
      this.repo = repo;
  }
  LibraryRepository repo = new LibraryRepository();
  LibraryService service = new LibraryService(repo);
  
  Another problem occurred during the testing phase, "userShouldBeAbleToBorrowBokk" because I used a List object supplied through the constructor which allowed a null list to be passed into the object. The problem was fixed by making User initialize its own list.
   
  private final List<Book> borrowedBooks;
  
  public User(int id, String name) {
      this.id = id;
      this.name = name;
      this.borrowedBooks = new ArrayList<>();
  }
