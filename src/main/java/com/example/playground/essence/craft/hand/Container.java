package com.example.playground.essence.craft.hand;

import com.example.playground.essence.Matter;

public abstract class Container extends Matter {
    protected String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
