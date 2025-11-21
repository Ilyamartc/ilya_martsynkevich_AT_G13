package com.example.homework.day10;
import java.util.stream.Stream;

public class WaterRunner {
    public static void main(String[] args) {
        Stream<Water> waters = Stream.of(
                new Water("Прозрачная", "Нет"),
                new Water("Прозрачная", "Нет"),
                new Water("Мутная", "Аммиачный"),
                new Water("Синяя", "Мятный")
        );

        int totalChars = waters
                .filter(w -> !w.getColor().equals("Прозрачная"))
                .sorted((w1, w2) -> w2.getSmell().compareTo(w1.getSmell()))
                .map(w -> {
                    String newSmell = w.getSmell().replace("ы", "ыы");
                    return new Water(w.getColor(), newSmell);
                })
                .map(Water::getSmell).reduce("", (s1, s2) -> s1 + s2).length();

        System.out.println("Количество букв в объединённой строке запахов: " + totalChars);
    }
}