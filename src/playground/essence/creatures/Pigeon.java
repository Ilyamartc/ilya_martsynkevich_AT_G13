package playground.essence.creatures;

import playground.essence.Flyable;

public class Pigeon extends Vertebrata implements Flyable {

    public Pigeon(int mass, String name) {
        super(mass, name); // вызываем конструктор родителя
    }

    @Override
    public void fly(String direction) {
        System.out.printf("I am %s, my name is %s and I am flying to %s%n",
                getClass().getSimpleName(), name, direction);
    }
}
