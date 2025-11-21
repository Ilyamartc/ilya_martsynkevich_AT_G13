package com.example.playground.essence.craft.field;

import com.example.playground.essence.Rideable;
import com.example.playground.essence.craft.Transportable;

public class Motorbike extends Vehicle implements Transportable, Rideable {
    public Motorbike(int mass, String name) {
        super(mass, name);
    }

    @Override
    public void drive(String direction) {
        System.out.printf("I am %s, my name is %s and I am driving to %s%n",
                getClass().getSimpleName(), name, direction);
    }
}
