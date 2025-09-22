package playground.essence.craft.air;

import playground.essence.Flyable;
import playground.essence.craft.Transportable;

public class Copter extends Aircraft implements Flyable, Transportable {
    @Override public void fly() { System.out.println(name + " is flying"); }
    @Override public void move() { System.out.println(name + " is moving"); }
}
