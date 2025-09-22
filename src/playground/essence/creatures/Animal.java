package playground.essence.creatures;

import playground.essence.Matter;

public abstract class Animal extends Matter {
    protected String name;
    public Animal() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void eat(Plant food) {
        System.out.printf("I am %s and I am eating %s%n", name, food.getName());
    }
}
