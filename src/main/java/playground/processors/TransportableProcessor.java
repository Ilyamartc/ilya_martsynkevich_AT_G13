package playground.processors;

import playground.essence.craft.Transportable;
import playground.essence.craft.field.Vehicle;

public class TransportableProcessor {
    public void runTransportable(Transportable transportable) {
        if (transportable instanceof Vehicle v) {
            v.move(0, 100);
        }
    }

    public void runTransportable(Transportable transportable, int pointA, int pointB) {
        if (transportable instanceof Vehicle v) {
            v.move(pointA, pointB);
        }
    }
}
