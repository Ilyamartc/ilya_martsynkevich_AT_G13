package playground.essence.material;

import playground.essence.Matter;

public class Diesel extends Matter implements Pourable, Powerable {

    @Override
    public void pour() {
        System.out.println("Pouring diesel");
    }

    @Override
    public void power() {
        System.out.println("Diesel is powering");
    }
}
