package com.example.playground.essence.craft.hand;

public class Bottle extends Container implements Storable {

    @Override
    public void store() {
        System.out.println(name + " is storing");
    }
}
