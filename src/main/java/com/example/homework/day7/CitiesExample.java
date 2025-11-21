package com.example.homework.day7;

import java.util.ArrayList;
import java.util.List;

public class CitiesExample {
    public static void main(String[] args) {
        List <String> cities = new ArrayList<>();
        cities.add("Minsk");
        cities.add("Moscow");
        cities.add("Berlin");

        for(String city : cities){
            System.out.println(city);
        }
        System.out.println();

        int sumLetters = 0;
        for(String city : cities){
            sumLetters += city.length();
        }
        System.out.println(sumLetters);

        System.out.println("\nГорода через пробел:");

        for(int i = 0; i < cities.size(); i++){
            System.out.print(cities.get(i)+ " ");
        }
        System.out.println();
    }
}