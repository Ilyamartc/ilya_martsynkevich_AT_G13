package playground.essence.craft.air;

import playground.essence.craft.Transportable;
import playground.essence.Flyable;

public class Rocket extends Aircraft implements Transportable, Flyable {

    public Rocket(int mass, String name) {
        super(mass, name);
    }

    @Override
    public void fly(String direction) {
        System.out.printf("I am %s, my name is %s and I am flying to %s%n",
                getClass().getSimpleName(), name, direction);
    }

    @Override
    public int move(int pointA, int pointB) {
        System.out.printf("I am %s, my name is %s and I am moving from point %d to point %d%n",
                getClass().getSimpleName(), name, pointA, pointB);
        return pointB - pointA;
    }
}
