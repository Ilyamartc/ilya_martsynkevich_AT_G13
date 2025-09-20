package playground.essence.creatures;

public class Beetle {
    public Beetle(String name, int mass) {
        super(name, mass);
    }

    public void nest(Carrot home) {
        if (this.mass < home.getMass()) {
            int familyMembers = home.getMass() / this.mass;
            System.out.println("I am " + this.name + " and I will nest there with " + familyMembers + " my family members!");
        } else {
            System.out.println("This carrot is too small for nesting :(");
        }
    }
}
