package playground.essence.craft.air;

import playground.essence.Flyable;
import playground.essence.craft.Transportable;

public class Copter extends Aircraft implements Flyable, Transportable {

    public Copter(int mass, String name) {
        super(mass, name);
    }

    @Override
    public void fly(String direction) {
        System.out.printf("I am %s, my name is %s and I am flying to %s%n",
                getClass().getSimpleName(), name, direction);
    }

    @Override
    public void move() {
        System.out.println(name + " is moving");
    }
}
