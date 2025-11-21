package com.example.playground.essence.material;

import com.example.playground.essence.Matter;

public class Diesel extends Matter implements Pourable, Powerable {

    @Override
    public void pour() {
        System.out.println("Pouring diesel");
    }

    @Override
    public void power() {
        System.out.println("Diesel is powering");
    }
}
