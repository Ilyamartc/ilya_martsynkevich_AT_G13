package playground.runners;

import playground.essence.Flyable;
import playground.essence.creatures.Fly;
import playground.essence.creatures.Mosquito;
import playground.essence.craft.air.Copter;
import playground.essence.craft.air.Plane;
import playground.essence.craft.air.Rocket;
import playground.essence.craft.air.Aircraft;
import playground.processors.FlyableProcessor;

public class FlyableProcessorRunner {

    public static void main(String[] args) {

        FlyableProcessor processor = new FlyableProcessor();
        Flyable flyableCopter = new Copter(223, "Mi8 Flyable");
        Flyable flyablePlane = new Plane(3452, "Boeing 837 Flyable");
        Flyable flyableRocket = new Rocket(7623, "Super Heavy Flyable");
        Flyable flyableFly = new Fly(23, "Domestica Flyable");
        Flyable flyableMosquito = new Mosquito(12, "Vulgaris Flyable");

        Aircraft aircraftCopter = new Copter(223, "Mi8 Aircraft");
        Aircraft aircraftPlane = new Plane(3452, "Boeing 837 Aircraft");
        Aircraft aircraftRocket = new Rocket(7623, "Super Heavy Aircraft");

        Fly insectFly = new Fly(23, "Domestica Insect");
        Mosquito insectMosquito = new Mosquito(12, "Vulgaris Insect");

        Copter aCopter = new Copter(223, "Mi8 Copter");
        Plane aPlane = new Plane(3452, "Boeing 837 Plane");
        Rocket aRocket = new Rocket(7623, "Super Heavy Rocket");
        Fly aFly = new Fly(23, "Domestica Fly");
        Mosquito aMosquito = new Mosquito(12, "Vulgaris Mosquito");

        processor.runFlyable(flyableCopter);
        processor.runFlyable(flyablePlane);
        processor.runFlyable(flyableRocket);
        processor.runFlyable(flyableFly);
        processor.runFlyable(flyableMosquito);

        processor.runFlyable(flyableCopter, "никуда");
        processor.runFlyable(flyablePlane, "повсюду");
    }
}
