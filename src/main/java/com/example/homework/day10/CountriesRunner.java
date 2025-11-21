package com.example.homework.day10;

import java.util.stream.Stream;

public class CountriesRunner {
    public static void main(String[] args) {
        Stream.of("Андора", "Португалия", "Англия", "Замбия")
                .filter(country -> country.matches(".*[аеёиоуыэюяAEIOUYaeiouy].*"))
                .filter(country -> country.length() < 7).map(String::toUpperCase)
                .map(country -> "\"" + country + "\"").forEach(System.out::println);
    }
}
