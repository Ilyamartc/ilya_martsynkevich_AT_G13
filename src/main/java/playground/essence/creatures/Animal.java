package playground.essence.creatures;

public abstract class Animal {

    protected int mass;
    protected String name;

    public Animal(int mass, String name) {
        this.mass = mass;
        this.name = name;
    }
    public int getMass() {
        return mass;
    }
    public void setMass(int mass) {
        this.mass = mass;
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
