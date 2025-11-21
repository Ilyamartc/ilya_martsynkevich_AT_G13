package com.example.playground.essence.creatures;

import com.example.playground.essence.Matter;

public abstract class Plant extends Matter {
    protected String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
