package playground.essence.craft.field;

import playground.essence.Rideable;
import playground.essence.craft.Transportable;

public class Moped extends Vehicle implements Transportable, Rideable {
    public Moped(int mass, String name) {
        super(mass, name);
    }

    @Override
    public void drive(String direction) {
        System.out.printf("I am %s, my name is %s and I am driving to %s%n",
                getClass().getSimpleName(), name, direction);
    }
}
