package playground.essence.craft.field;

public class Vehicle {
    protected int mass;
    protected String name;

    public Vehicle(int mass, String name) {
        this.mass = mass;
        this.name = name;
    }

    public int move(int pointA, int pointB) {
        System.out.printf("I am %s, my name is %s and I am moving from point %d to point %d%n",
                getClass().getSimpleName(), name, pointA, pointB);
        return pointB - pointA;
    }
}
