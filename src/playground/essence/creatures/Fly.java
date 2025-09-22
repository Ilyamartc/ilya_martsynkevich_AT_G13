package playground.essence.creatures;

import playground.essence.Flyable;

public class Fly extends Insect implements Flyable {
    @Override
    public void fly() {
        System.out.println(name + " is flying");
    }
}
