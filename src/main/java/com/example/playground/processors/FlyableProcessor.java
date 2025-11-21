package com.example.playground.processors;

import com.example.playground.essence.Flyable;
import com.example.playground.utils.DirectionGenerator;

public class FlyableProcessor {

    public void runFlyable(Flyable flyable) {
        String direction = DirectionGenerator.generateDirection();
        flyable.fly(direction);
    }

    public void runFlyable(Flyable flyable, String direction) {
        flyable.fly(direction);
    }
}
