package com.example.playground.processors;

import com.example.playground.essence.Rideable;
import com.example.playground.utils.DirectionGenerator;

public class RideableProcessor {
    public void runRideable(Rideable rideable) {
        String randomDirection = DirectionGenerator.generateDirection();
        rideable.drive(randomDirection);
    }

    public void runRideable(Rideable rideable, String direction) {
        rideable.drive(direction);
    }
}
