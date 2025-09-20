package lesson1.bubbles;

public class Bubble {
    private final double volume = 0.3;
    private String gas;
    public double getVolume() {
        return volume;
    }
    public String getGas() {
        return gas;
    }
    public void pop() {
        System.out.println("Cramp! " + gas);
    }
    public Bubble (String gas) {
        this.gas = gas;
    }
}