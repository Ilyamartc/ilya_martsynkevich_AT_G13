package com.example.playground.essence.creatures;

import com.example.playground.essence.Flyable;

public class Raven extends Vertebrata implements Flyable {

    public Raven(int mass, String name) {
        super(mass, name);
    }
    @Override
    public void fly(String direction) {
        System.out.printf("I am %s, my name is %s and I am flying to %s%n", getClass().getSimpleName(), name, direction);
    }
}
