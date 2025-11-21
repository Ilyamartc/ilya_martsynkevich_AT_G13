package com.example.homework.day7;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FiguresTask {

    public static void main(String[] args) {

        try {
            List<String> figures = new ArrayList<>(Arrays.asList("Овал", "Прямоугольник", "Круг", "Квадрат", "Эллипс"));

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("figures.txt"))) {
                for (String figure : figures) {
                    writer.write(figure + "-");
                }
            }

            long countNoI = figures.stream().filter(f -> !f.contains("и")).count();
            System.out.println("Количество фигур без буквы 'и': " + countNoI);

            System.out.println("По индексу через пробел:");
            for (int i = 0; i < figures.size(); i++) {
                System.out.print(figures.get(i) + " ");
            }
            System.out.println();

            figures.add(2, "Треугольник");

            System.out.println("Список после вставки Треугольника:");
            for (String figure : figures) {
                System.out.print(figure + " ");
            }
            System.out.println();

        }
        catch (IOException e) {
            System.out.println("Исключение: " + e.getClass().getSimpleName());
            System.out.println("Причина: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Исключение: " + e.getClass().getSimpleName());
            System.out.println("Причина: " + e.getMessage());
        }
    }
}
