package playground.essence.creatures;

public abstract class Vertebrata extends Animal {

    public Vertebrata(int mass, String name) {
        super(mass, name); // вызываем конструктор родителя
    }

    public void eat(Insect food) {
        System.out.printf("I am %s and I am eating %s%n", name, food.getName());
    }
}
