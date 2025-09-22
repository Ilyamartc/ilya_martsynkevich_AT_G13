package playground.essence.material;

import playground.essence.Matter;

public class Petrol extends Matter implements Pourable, Powerable {
    @Override public void pour() { System.out.println("Pouring petrol"); }
    @Override public void power() { System.out.println("Petrol is powering"); }
}
