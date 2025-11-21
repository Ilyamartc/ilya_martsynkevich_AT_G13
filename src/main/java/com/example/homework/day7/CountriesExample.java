package com.example.homework.day7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CountriesExample {
    public static void main(String[] args) throws IOException {
        List<String> countries = new ArrayList<String>();
        countries.add("Андора");
        countries.add("Португалия");
        countries.add("Англия");
        countries.add("Замбия");

        for (String country : countries) {
            System.out.print(country);
        }
    }
}
