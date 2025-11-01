package playground.utils;

import java.util.Random;

public class DirectionGenerator {
    private static final Random random = new Random();

    public static String generateDirection() {
        int rand = random.nextInt(39) + 1;
        if (rand >= 1 && rand <= 9) {
            return "NORTH";
        } else if (rand >= 10 && rand <= 19) {
            return "SOUTH";
        } else if (rand >= 20 && rand <= 29) {
            return "WEST";
        } else {
            return "EAST";
        }
    }
}
