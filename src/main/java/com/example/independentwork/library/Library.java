package com.example.independentwork.library;

public class Library {
    private String libraryName;
    private Book[] books;

    public Library(String libraryName) {
        this.libraryName = libraryName;
    }

    public void addBooks(Book[] books) {
        this.books = books;
    }

    public void printAllBooks() {
        System.out.println("Библиотека: " + libraryName);
        if (books != null) {
            for (Book book : books) {
                book.printInfo();
            }
        }
        else {
            System.out.println("В библиотеке пока нет книг.");
        }
    }
}