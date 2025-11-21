package com.example.homework.day7;

import java.util.Arrays;
import java.util.List;

public class BubblesExample {
    public static void main(String[] args) {
        List<Bubble> bubbles = Arrays.asList(
                new Bubble(2, "CO2"),
                new Bubble(4, "O2"),
                new Bubble(5, "CO")
        );

        System.out.println("Объемы пузырей:");
        for (Bubble bubble : bubbles) {
            System.out.print(bubble.getVolume() + " ");
        }
        System.out.println();

        System.out.println("\nИмена пузырей:");
        for (Bubble bubble : bubbles) {
            System.out.print(bubble.getName() + " ");
        }
        System.out.println();

        int totalVolume = 0;
        for (Bubble bubble : bubbles) {
            totalVolume += bubble.getVolume();
        }
        System.out.println("\nОбщий объем всех пузырей: " + totalVolume);

        System.out.println("\nСписок пузырей:");
        for (int i = 0; i < bubbles.size(); i++) {
            System.out.println(bubbles.get(i).toString());
        }
    }
}

