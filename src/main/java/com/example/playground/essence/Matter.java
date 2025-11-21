package com.example.playground.essence;

public abstract class Matter {
    protected int mass;
    protected String name;

    public Matter() {
    }

    public Matter(int mass, String name) {
        this.mass = mass;
        this.name = name;
    }

    public int getMass() {
        return mass;
    }

    public String getName() {
        return name;
    }
}
