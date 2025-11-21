package com.example.homework.day10;

import java.util.Arrays;
import java.util.stream.Stream;

public class BirdsRunner {
    public static void main(String[] args) {
        Stream.of("Чайка", "Дрозд", "Бусел", "Голубь", "Воробей", "Цапля")
                .map(bird -> bird.replace('о', 'а'))
                .map(String::toLowerCase)
                .reduce((a, b) -> a + b)
                .ifPresent(result -> Arrays.stream(
                                result.replace("ь", "")
                                        .split("б"))
                        .forEach(part -> System.out.println("--" + part + "--")));
    }
}
