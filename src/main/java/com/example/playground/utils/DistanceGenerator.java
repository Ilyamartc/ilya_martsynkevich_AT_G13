package com.example.playground.utils;

import java.util.Random;

public class DistanceGenerator {

    private static final Random random = new Random();

    public static int generateDistance() {
        int distance = random.nextInt(99) + 1;
        System.out.println("DistanceGenerator: I have generated distance with value: " + distance);
        return distance;
    }
}
