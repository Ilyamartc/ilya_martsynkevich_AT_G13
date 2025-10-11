package playground.runners;

import playground.essence.Rideable;
import playground.essence.craft.field.*;
import playground.processors.RideableProcessor;

public class RideableProcessorRunner {
    public static void main(String[] args) {
        RideableProcessor processor = new RideableProcessor();

        Rideable rideableCar = new Car(223, "Lada Rideable");
        Rideable rideableMoped = new Moped(3452, "Honda EM1 Rideable");
        Rideable rideableMotorbike = new Motorbike(7623, "Suzuki GSX-R1000 Rideable");

        processor.runRideable(rideableCar);
        processor.runRideable(rideableMoped);
        processor.runRideable(rideableMotorbike);

        processor.runRideable(rideableCar, "никуда");
        processor.runRideable(rideableMotorbike, "повсюду");
    }
}
