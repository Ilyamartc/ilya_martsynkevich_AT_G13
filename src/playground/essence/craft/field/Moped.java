package playground.essence.craft.field;

import playground.essence.craft.Transportable;
import playground.essence.craft.Rideable;

public class Moped extends Vehicle implements Transportable, Rideable {
    @Override public void move() { System.out.println(name + " is moving"); }
    @Override public void ride() { System.out.println(name + " is being ridden"); }
}
