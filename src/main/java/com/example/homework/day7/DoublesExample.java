package com.example.homework.day7;

import java.util.Arrays;
import java.util.List;

public class DoublesExample {
    public static void main(String[] args) {
        List<Double> doubles = Arrays.asList(33.42, 34.3, 0.79, 2.3426, 6.8, 13.24, 5.5, 769.9);

        System.out.println("Числа через пробел:");
        for (Double num : doubles) {
            System.out.print(num + " ");
        }
        System.out.println();

        double product = 1.0;
        for (Double num : doubles) {
            product *= num;
        }
        System.out.println("\nПроизведение всех чисел: " + product);

        double fractionalSum = 0.0;
        for (Double num : doubles) {
            fractionalSum += num - Math.floor(num);
        }
        System.out.println("Сумма всех дробных частей: " + fractionalSum);

        System.out.println("\nЦелые части через пробел:");
        for (int i = 0; i < doubles.size(); i++) {
            System.out.print((int) Math.floor(doubles.get(i)) + " ");
        }
        System.out.println();
    }
}
