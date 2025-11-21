package com.example.homework.day10;

import com.example.project.bubbles.Bubble;
import java.util.*;
import java.util.stream.*;

public class BubblesRunner {
    public static void main(String[] args) {
        Random random = new Random();

        Stream<Double> doubles = Stream.of(33.42, 34.3, 0.79, 2.3426, 6.8, 13.24, 5.5, 769.9);

        List<Bubble> bubbles = doubles.map(Math::round)
                .flatMap(n -> random.ints(n.intValue(), 0, n.intValue() + 1)
                        .distinct()
                        .limit(n.intValue())
                        .boxed())
                .flatMap(num -> IntStream.range(0, num).mapToObj(i -> new Bubble(num.doubleValue(), "Bubble vol-" + num)))
                .peek(System.out::println)
                .toList();

        long totalBubbles = bubbles.size();
        System.out.println("Total bubbles: " + totalBubbles);

        double totalVolume = bubbles.stream()
                .mapToDouble(Bubble::getVolume)
                .sum();
        System.out.println("Total volume: " + totalVolume);
    }
}
