package com.example.homework.day10;

import com.example.project.bubbles.Bubble;
import java.util.*;
import java.util.stream.*;
import java.io.*;

public class ChairRunner {
    public static void main(String[] args) {
        Random random = new Random();

        Stream<Chair> furniture = Stream.of(
                new Chair(120, 40),
                new Chair(90, 30),
                new Chair(100, 50),
                new Chair(110, 45)
        );

        List<Chair> filteredSorted = furniture
                .filter(c -> c.getHeight() >= 100 && c.getWidth() >= 50)
                .sorted(Comparator.comparingInt(Chair::getHeight)
                        .thenComparing(Comparator.comparingInt(Chair::getWidth)
                                .reversed())).toList();

        List<Chair> modifiedChairs = filteredSorted.stream()
                .map(c -> new Chair(c.getHeight() / 2, c
                        .getWidth() * (random.nextInt(6) + 3))).toList();

        OptionalInt maxValue = modifiedChairs.stream()
                .mapToInt(c -> c.getHeight() * c.getWidth())
                .distinct()
                .max();

        if (maxValue.isPresent()) {
            int value = maxValue.getAsInt();

            String name = Arrays.stream(String.valueOf(value).split("")).map(ChairRunner::digitToWord).collect(Collectors.joining(" "));

            Bubble resultBubble = new Bubble(value, name);

            try (PrintWriter writer = new PrintWriter("chairBubble.txt", "UTF-8")) {
                writer.println(resultBubble);
                System.out.println("Bubble записан в chairBubble.txt");
            } catch (IOException e) {
                System.err.println("Ошибка записи в файл: " + e.getMessage());
            }
        }
    }
    private static String digitToWord(String digit) {
        return switch (digit) {
            case "0" -> "zero";
            case "1" -> "one";
            case "2" -> "two";
            case "3" -> "three";
            case "4" -> "four";
            case "5" -> "five";
            case "6" -> "six";
            case "7" -> "seven";
            case "8" -> "eight";
            case "9" -> "nine";
            default -> "";
        };
    }
}