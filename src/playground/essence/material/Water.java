package playground.essence.material;

import playground.essence.Matter;

public class Water extends Matter implements Pourable {

    @Override
    public void pour() {
        System.out.println("Pouring water");
    }
}
