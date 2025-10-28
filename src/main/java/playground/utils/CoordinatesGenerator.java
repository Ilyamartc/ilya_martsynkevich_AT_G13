package playground.utils;

import java.util.Random;

public class CoordinatesGenerator {

    private static final Random random = new Random();

    public static int generateCoordinate() {
        int value = random.nextInt(79) + 1;
        System.out.printf("CoordinatesGenerator: I have generated point with value: %d%n", value);
        return value;
    }
}
