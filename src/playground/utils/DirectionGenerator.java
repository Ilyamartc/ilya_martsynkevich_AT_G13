package playground.utils;

import java.util.concurrent.ThreadLocalRandom;

public class DirectionGenerator {

    public static String generateDirection() {
        int rand = ThreadLocalRandom.current().nextInt(1, 40);
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
