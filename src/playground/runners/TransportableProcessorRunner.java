package playground.runners;

import playground.essence.Flyable;
import playground.essence.craft.Transportable;
import playground.essence.craft.Rideable;
import playground.essence.craft.air.*;
import playground.essence.craft.field.*;
import playground.processors.TransportableProcessor;

public class TransportableProcessorRunner {
    public static void main(String[] args) {
        TransportableProcessor processor = new TransportableProcessor();

        // Создание объектов
        Transportable transportableCopter = new Copter(223, "AW109 Transportable");
        Transportable transportablePlane = new Plane(3452, "Airbus a380 Transportable");
        Transportable transportableRocket = new Rocket(7623, "Soyuz-FG Transportable");
        Transportable transportableCar = new Car(23, "Tesla X Transportable");
        Transportable transportableMoped = new Moped(12, "Honda EM1 Transportable");
        Transportable transportableMotorbike = new Motorbike(12, "Suzuki GSX-R1000 Transportable");

        Aircraft aircraftCopter = new Copter(223, "AW109 Aircraft");
        Aircraft aircraftPlane = new Plane(3452, "Airbus a380 Aircraft");
        Aircraft aircraftRocket = new Rocket(7623, "Soyuz-FG Aircraft");

        Vehicle vehicleCar = new Car(23, "Tesla X Vehicle");
        Vehicle vehicleMoped = new Moped(12, "Honda EM1 Vehicle");
        Vehicle vehicleMotorbike = new Motorbike(12, "Suzuki GSX-R1000 Vehicle");

        Flyable flyableCopter = new Copter(223, "AW109 Flyable");
        Flyable flyablePlane = new Plane(3452, "Airbus a380 Flyable");
        Flyable flyableRocket = new Rocket(7623, "Soyuz-FG Flyable");

        Rideable rideableCar = new Car(23, "Tesla X Rideable");
        Rideable rideableMoped = new Moped(12, "Honda EM1 Rideable");
        Rideable rideableMotorbike = new Motorbike(12, "Suzuki GSX-R1000 Rideable");

        Copter aCopter = new Copter(223, "AW109 Copter");
        Plane aPlane = new Plane(3452, "Airbus a380 Plane");
        Rocket aRocket = new Rocket(7623, "Soyuz-FG Rocket");
        Car aCar = new Car(23, "Tesla X Car");
        Moped aMoped = new Moped(12, "Honda EM1 Moped");
        Motorbike aMotorbike = new Motorbike(12, "Suzuki GSX-R1000 Motorbike");

        // Запуск через processor
        processor.runTransportable(transportableCopter);
        processor.runTransportable(transportablePlane);
        processor.runTransportable(transportableRocket);
        processor.runTransportable(transportableCar);
        processor.runTransportable(transportableMoped);
        processor.runTransportable(transportableMotorbike);

        // Следующие объекты НЕ Transportable напрямую, поэтому нельзя:
        // processor.runTransportable(aircraftCopter); // Aircraft не реализует Transportable
        // processor.runTransportable(vehicleCar); // Vehicle не реализует Transportable
        // processor.runTransportable(flyableCopter); // Flyable не является Transportable
        // processor.runTransportable(rideableCar); // Rideable не является Transportable

        // Эти объекты конкретных классов можно:
        processor.runTransportable(aCopter);
        processor.runTransportable(aPlane);
        processor.runTransportable(aRocket);
        processor.runTransportable(aCar);
        processor.runTransportable(aMoped);
        processor.runTransportable(aMotorbike);

        // Тест с конкретными точками
        processor.runTransportable(transportableCopter, 23, 242);
        processor.runTransportable(transportableCar, 93, 7);
    }
}
