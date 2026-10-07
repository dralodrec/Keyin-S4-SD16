package com.library.model;

public class Book {
    private String Isbn;
    private String Title;
    private String Author;
    private String category;
    private boolean isAvailable;

    public Book(String isbn, String title, String author, String category, boolean isAvailable) {
        this.Isbn = isbn;
        this.Title = title;
        this.Author = author;
        this.category = category;
        this.isAvailable = isAvailable;
    }
 
    public String getIsbn() { return Isbn;}
    public void setIsbn(String isbn) {
        Isbn = isbn;
    }
    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }
    public String getAuthor() {
        return Author;
    }
    public void setAuthor(String author) {
        Author = author;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void borrow() {
        isAvailable = false;
    }
    public void returnBook() {
        isAvailable = true;
    }


    public String toString() {
        return "Book [Isbn=" + Isbn + ", Title=" + Title + ", Author=" + Author + ", category=" + category + ", isAvailable=" + isAvailable + "]";
    }
}

//book.borrow();
//book.returnBook();
//book.isAvailable();