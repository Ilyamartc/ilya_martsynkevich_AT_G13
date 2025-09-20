package playground.essence.creatures;

abstract class Vertebrata {
    public Vertebrata(String name, int mass) {
        super(name, mass);
    }

    public void eat(Insect food) {
        System.out.println("I am " + this.name + " and I am eating " + food.getName());
    }
}
