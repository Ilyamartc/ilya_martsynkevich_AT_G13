package playground.essence.craft.field;

import playground.essence.Matter;

public abstract class Vehicle extends Matter {

    protected String name;

    public Vehicle(int mass, String name) {
        super(mass, name);
        this.name = name;  // сохраняем имя
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
