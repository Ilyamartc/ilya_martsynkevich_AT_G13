package com.example.homework.day7;

import java.util.Arrays;
import java.util.List;

class Bubble {
    private int volume;
    private String name;

    public Bubble(int volume, String name) {
        this.volume = volume;
        this.name = name;
    }

    public int getVolume() {
        return volume;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Bubble{name='" + name + "', volume=" + volume + "}";
    }
}
