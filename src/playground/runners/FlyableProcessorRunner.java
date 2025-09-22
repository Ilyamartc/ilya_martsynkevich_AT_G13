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

        // Создание объектов Flyable
        Flyable flyableCopter = new Copter(223, "Mi8 Flyable");
        Flyable flyablePlane = new Plane(3452, "Boeing 837 Flyable");
        Flyable flyableRocket = new Rocket(7623, "Super Heavy Flyable");
        Flyable flyableFly = new Fly(23, "Domestica Flyable");
        Flyable flyableMosquito = new Mosquito(12, "Vulgaris Flyable");

        // Создание объектов Aircraft
        Aircraft aircraftCopter = new Copter(223, "Mi8 Aircraft");
        Aircraft aircraftPlane = new Plane(3452, "Boeing 837 Aircraft");
        Aircraft aircraftRocket = new Rocket(7623, "Super Heavy Aircraft");

        // Создание объектов Insect
        Fly insectFly = new Fly(23, "Domestica Insect");
        Mosquito insectMosquito = new Mosquito(12, "Vulgaris Insect");

        // Создание объектов с конкретными типами
        Copter aCopter = new Copter(223, "Mi8 Copter");
        Plane aPlane = new Plane(3452, "Boeing 837 Plane");
        Rocket aRocket = new Rocket(7623, "Super Heavy Rocket");
        Fly aFly = new Fly(23, "Domestica Fly");
        Mosquito aMosquito = new Mosquito(12, "Vulgaris Mosquito");

        // Запуск FlyableProcessor.runFlyable с объектами Flyable
        processor.runFlyable(flyableCopter);
        processor.runFlyable(flyablePlane);
        processor.runFlyable(flyableRocket);
        processor.runFlyable(flyableFly);
        processor.runFlyable(flyableMosquito);

        // Ниже закомментированные вызовы — не Flyable напрямую
        // processor.runFlyable(aircraftCopter); // Aircraft не реализует Flyable напрямую
        // processor.runFlyable(aircraftPlane);
        // processor.runFlyable(aircraftRocket);
        // processor.runFlyable(insectFly); // Insect не все Flyable
        // processor.runFlyable(insectMosquito);
        // processor.runFlyable(aCopter); // Уже Flyable
        // processor.runFlyable(aPlane);
        // processor.runFlyable(aRocket);
        // processor.runFlyable(aFly); // Уже Flyable
        // processor.runFlyable(aMosquito); // Уже Flyable

        // Запуск runFlyable с двумя Flyable и конкретными направлениями
        processor.runFlyable(flyableCopter, "никуда");
        processor.runFlyable(flyablePlane, "повсюду");
    }
}
