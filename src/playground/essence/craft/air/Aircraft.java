package playground.essence.craft.air;

import playground.essence.Matter;
public abstract class Aircraft extends Matter {
    protected String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}