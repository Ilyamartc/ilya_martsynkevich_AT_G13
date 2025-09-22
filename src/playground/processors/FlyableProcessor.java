package playground.processors;

import playground.essence.Flyable;
import playground.utils.DirectionGenerator;

public class FlyableProcessor {

    public void runFlyable(Flyable flyable) {
        String direction = DirectionGenerator.generateDirection();
        flyable.fly(direction);
    }

    public void runFlyable(Flyable flyable, String direction) {
        flyable.fly(direction);
    }
}
