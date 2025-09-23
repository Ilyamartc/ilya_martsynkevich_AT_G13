package playground.essence.craft.field;

import playground.essence.craft.Transportable;
import playground.essence.craft.Rideable;

public class Motorbike extends Vehicle implements Transportable, Rideable {

    public Motorbike(int mass, String name) {
        super(mass, name);
    }

    @Override
    public int move(int pointA, int pointB) {
        System.out.printf("I am %s, my name is %s and I am moving from point %d to point %d%n",
                getClass().getSimpleName(), name, pointA, pointB);
        return pointB - pointA;
    }

    @Override
    public int ride(int pointA, int pointB) {
        System.out.printf("I am %s, my name is %s and I am riding from point %d to point %d%n",
                getClass().getSimpleName(), name, pointA, pointB);
        return pointB - pointA;
    }
}
