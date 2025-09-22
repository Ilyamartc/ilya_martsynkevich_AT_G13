package playground.essence.creatures;

import playground.essence.Flyable;

public class Mosquito extends Insect implements Flyable {

    public Mosquito(int mass, String name) {
        super(mass, name);
    }

    @Override
    public void fly(String direction) {
        System.out.printf("I am %s, my name is %s and I am flying to %s%n",
                getClass().getSimpleName(), name, direction);
    }
}
