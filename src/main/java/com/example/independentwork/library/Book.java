package com.example.independentwork.library;

public class Book {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
    public void printInfo(){
        System.out.println("Книга " + title + " автора " + author + " была опубликована " + year);
    }

}

