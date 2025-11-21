package com.example.homework.day5;


public class Column {
    public static void column(String[] args) {
        String text = "Hello world";
        String [] letters = text.split("");
        for (String letter : letters) {
            System.out.println(letter);
        }
    }
}
