package playground.essence.craft.field;

import playground.essence.Matter;

public abstract class Vehicle extends Matter {
    protected String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
