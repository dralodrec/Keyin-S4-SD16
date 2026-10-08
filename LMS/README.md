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
