package playground.essence.creatures;

import playground.essence.Matter;

public abstract class Plant extends Matter {
    protected String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
