package playground.essence.creatures;
import playground.essence.Matter;

abstract class Plant {
    protected String name;

    public Plant(String name, int mass) {
        super(mass);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
