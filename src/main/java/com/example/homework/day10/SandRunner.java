package com.example.homework.day10;

import java.util.*;
import java.util.stream.*;
import java.io.*;

public class SandRunner {
    public static void main(String[] args) {
        List<Sand> sandbox = new ArrayList<>(Arrays.asList(
                new Sand(12, "Речной"),
                new Sand(8, "Речной"),
                new Sand(15, "Карьерный"),
                new Sand(7, "Карьерный"),
                new Sand(11, "Речной")
        ));

        Map<Integer, String> sandMap = sandbox.stream().filter(s -> s.getWeight() > 9 && s.getName().contains("ч")).sorted(Comparator.comparingInt(Sand::getWeight)).map(s -> new Sand(s.getWeight() * 2, s.getName().toUpperCase())).collect(Collectors.toMap(
                        Sand::getWeight,
                        Sand::getName,
                        (existing, replacement) -> existing
                ));

        try (PrintWriter writer = new PrintWriter("sandMap.txt", "UTF-8")) {
            sandMap.forEach((weight, name) -> writer.println(name + ":" + weight));
            System.out.println("Map записан в sandMap.txt");
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}