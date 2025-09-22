package playground.essence.creatures;

import playground.essence.Flyable;

public class Mosquito extends Insect implements Flyable {
    @Override
    public void fly() {
        System.out.println(name + " is flying");
    }
}