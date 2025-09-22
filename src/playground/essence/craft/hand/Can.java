package playground.essence.craft.hand;

public class Can extends Container implements Storable {
    @Override public void store() { System.out.println(name + " is storing"); }
}