package com.example.homework.day8.separatePack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityRunner {
    public static void main(String[] args) {
        List<Cities> cities = new ArrayList<>();

        cities.add(new Cities("Москва", 12000000));
        cities.add(new Cities("Нью-Йорк", 8400000));
        cities.add(new Cities("Париж", 2200000));

        System.out.println("Названия городов:");
        for (Cities city : cities) {
            System.out.print(city.getName() + " ");
        }
        System.out.println();

        System.out.println("Население городов:");
        for (Cities city : cities) {
            System.out.print(city.getPopulation() + " ");
        }
        System.out.println();

        Map<String, Cities> cityMap = new HashMap<>();
        for (Cities city : cities) {
            cityMap.put(city.getName(), city);
        }

        int totalPopulation = 0;
        for (Cities city : cityMap.values()) {
            totalPopulation += city.getPopulation();
        }
        System.out.println("\nОбщая популяция всех городов: " + totalPopulation);
    }
}
