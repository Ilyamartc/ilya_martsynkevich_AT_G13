package playground.runners;

import playground.essence.craft.Transportable;
import playground.essence.craft.field.*;
import playground.processors.TransportableProcessor;

public class TransportableProcessorRunner {
    public static void main(String[] args) {
        TransportableProcessor processor = new TransportableProcessor();

        Transportable transportableCar = new Car(223, "Lada Transportable");
        Transportable transportableMoped = new Moped(3452, "Honda EM1 Transportable");
        Transportable transportableMotorbike = new Motorbike(7623, "Suzuki GSX-R1000 Transportable");

        processor.runTransportable(transportableCar);
        processor.runTransportable(transportableMoped);
        processor.runTransportable(transportableMotorbike);

        processor.runTransportable(transportableCar, 10, 50);
        processor.runTransportable(transportableMotorbike, 5, 20);
    }
}
