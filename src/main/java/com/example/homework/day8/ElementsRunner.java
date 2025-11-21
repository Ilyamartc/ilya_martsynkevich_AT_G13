package com.example.homework.day8;

import java.util.HashMap;
import java.util.Map;

public class ElementsRunner {
    public static void main(String[] args) {
        Map<Integer, String> elements = new HashMap<>();

        elements.put(1, "Silicon");
        elements.put(2, "Sulfur");
        elements.put(3, "Argon");
        elements.put(4, "Calcium");
        elements.put(5, "Chromium");
        elements.put(6, "Iron");
        elements.put(7, "Zinc");

        for (String value : elements.values()) {
            System.out.print(value + " ");
        }
        System.out.println();

        int count = 0;
        for (String value : elements.values()) {
            if (value.toLowerCase().contains("u")) {
                count++;
            }
        }
        System.out.println("Количество элементов с 'u': " + count);

        for (Map.Entry<Integer, String> entry : elements.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        elements.put(4, "Selenium");

        elements.remove(2);

        System.out.println("\nПосле изменений:");
        for (Map.Entry<Integer, String> entry : elements.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
