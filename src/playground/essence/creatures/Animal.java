package playground.essence.creatures;
import playground.essence.Matter;

abstract class Animal extends Matter {
    protected String name;

    public Animal(String name, int mass) {
        super(mass);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void eat(Plant food) {
        System.out.println("I am " + this.name + " and I am eating " + food.getName());
    }
}
