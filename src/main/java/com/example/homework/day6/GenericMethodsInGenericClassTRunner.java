package com.example.homework.day6;

import com.example.playground.essence.Flyable;
import com.example.playground.essence.craft.Transportable;
import com.example.playground.essence.craft.air.Copter;
import com.example.playground.essence.craft.air.Plane;
import com.example.playground.essence.craft.air.Rocket;
import com.example.playground.essence.creatures.Mosquito;
import com.example.playground.essence.creatures.Pigeon;
import com.example.playground.essence.creatures.Raven;
import com.example.playground.essence.craft.field.Car;
import com.example.playground.essence.craft.field.Moped;
import com.example.playground.essence.craft.field.Motorbike;

public class GenericMethodsInGenericClassTRunner {
    public static void main(String[] args) {

        GenericMethodsInGenericClassT<String> genericMethodsInGenericClassT1 = new GenericMethodsInGenericClassT<>("Hello world");
        genericMethodsInGenericClassT1.genericMethodOneGenArg("Test");
        System.out.println(genericMethodsInGenericClassT1.genericMethodTwoGenArgs("A", "B"));
        genericMethodsInGenericClassT1.genericMethodHalfGenArgs("X", "Hello World");

        GenericMethodsInGenericClassT<Integer> genericMethodsInGenericClassT2 = new GenericMethodsInGenericClassT<>(111);
        genericMethodsInGenericClassT2.genericMethodOneGenArg(42);
        System.out.println(genericMethodsInGenericClassT2.genericMethodTwoGenArgs(1, 2));
        genericMethodsInGenericClassT2.genericMethodHalfGenArgs(100, "IntegerTest");

        GenericMethodsInGenericClassT<Double> genericMethodsInGenericClassT3 = new GenericMethodsInGenericClassT<>(11.1);
        genericMethodsInGenericClassT3.genericMethodOneGenArg(2.718);
        System.out.println(genericMethodsInGenericClassT3.genericMethodTwoGenArgs(1.1, 2.2));
        genericMethodsInGenericClassT3.genericMethodHalfGenArgs(3.14, "Pi");


        Flyable copter = new Copter(4, "Apache");
        GenericMethodsInGenericClassT<Flyable> flyableObj = new GenericMethodsInGenericClassT<>(copter);
        flyableObj.genericMethodOneGenArg(copter);
        System.out.println(flyableObj.genericMethodTwoGenArgs(copter, new Copter(4, "Apache")));
        flyableObj.genericMethodHalfGenArgs(copter, "FlyableTest");

        Flyable plane = new Plane(5, "Boing 747");
        GenericMethodsInGenericClassT<Flyable> flyableObj1 = new GenericMethodsInGenericClassT<>(plane);
        flyableObj1.genericMethodOneGenArg(plane);
        System.out.println(flyableObj1.genericMethodTwoGenArgs(plane, new Plane(4, "Boing 747")));
        flyableObj1.genericMethodHalfGenArgs(plane, "FlyableTestT");

        Flyable rocket = new Rocket(5, "Boing 747");
        GenericMethodsInGenericClassT<Flyable> flyableObj2 = new GenericMethodsInGenericClassT<>(rocket);
        flyableObj2.genericMethodOneGenArg(rocket);
        System.out.println(flyableObj2.genericMethodTwoGenArgs(rocket, new Rocket(5, "Apalon")));
        flyableObj2.genericMethodHalfGenArgs(plane, "FlyableTestTe");

        Flyable mosquito = new Mosquito(6, "Troll");
        GenericMethodsInGenericClassT<Flyable> flyableObj3 = new GenericMethodsInGenericClassT<>(mosquito);
        flyableObj3.genericMethodOneGenArg(mosquito);
        System.out.println(flyableObj3.genericMethodTwoGenArgs(mosquito, new Mosquito(5, "Apalon")));
        flyableObj3.genericMethodHalfGenArgs(mosquito, "FlyableTestTes");

        Flyable pigeon = new Pigeon(7, "222www");
        GenericMethodsInGenericClassT<Flyable> flyableObj4 = new GenericMethodsInGenericClassT<>(pigeon);
        flyableObj4.genericMethodOneGenArg(pigeon);
        System.out.println(flyableObj4.genericMethodTwoGenArgs(mosquito, new Pigeon(5, "Ali")));
        flyableObj4.genericMethodHalfGenArgs(pigeon, "FlyableTestTest");

        Flyable raven = new Raven(8, "asdasd");
        GenericMethodsInGenericClassT<Flyable> flyableObj5 = new GenericMethodsInGenericClassT<>(raven);
        flyableObj4.genericMethodOneGenArg(raven);
        System.out.println(flyableObj5.genericMethodTwoGenArgs(raven, new Raven(6, "Sam")));
        flyableObj5.genericMethodHalfGenArgs(raven, "FlyableTestTestT");

        Transportable motorbike = new Motorbike(12, "Ducatti");
        GenericMethodsInGenericClassT<Transportable> transportableObj1 = new GenericMethodsInGenericClassT<>(motorbike);
        transportableObj1.genericMethodOneGenArg(motorbike);
        System.out.println(transportableObj1.genericMethodTwoGenArgs(motorbike, new Motorbike(6, "Sam")));
        transportableObj1.genericMethodHalfGenArgs(motorbike, "TransportableTestTestTe");

        Transportable moped = new Moped(13, "Fiat");
        GenericMethodsInGenericClassT<Transportable> transportableObj2 = new GenericMethodsInGenericClassT<>(moped);
        transportableObj2.genericMethodOneGenArg(moped);
        System.out.println(transportableObj2.genericMethodTwoGenArgs(moped, new Moped(7, "LLL")));
        transportableObj2.genericMethodHalfGenArgs(moped, "TransportableTestTestTes");

        Transportable car = new Car(14, "Seat");
        GenericMethodsInGenericClassT<Transportable> transportableObj3 = new GenericMethodsInGenericClassT<>(car);
        transportableObj3.genericMethodOneGenArg(car);
        System.out.println(transportableObj3.genericMethodTwoGenArgs(car, new Car(8, "LLdsdL")));
        transportableObj3.genericMethodHalfGenArgs(car, "TransportableTestTestTest");

    }
}
