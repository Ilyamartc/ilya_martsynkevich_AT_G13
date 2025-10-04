package homework.day7;

import playground.essence.craft.hand.Bottle;
import java.io.*;

class SerializableBottle implements Serializable {
    private String model;
    private int size;

    public SerializableBottle(Bottle bottle) {
        this.model = "default";
        this.size = 0;
    }

    @Override
    public String toString() {
        return "SerializableBottle{model='" + model + "', size=" + size + "}";
    }
}