package project.bubbles;

public class SparklingWater extends Water {
    public SparklingWater(String color, String transparency, String smell, double temperature) {
        super(color, transparency, smell, temperature);
    }
    private Bubble[] bubbles;
    public void pump(Bubble[] bubbles) {
        this.bubbles = bubbles;
    }
    public void degas() {
        if (bubbles != null) {
            for (Bubble b : bubbles) {
                b.pop();
            }
            bubbles = null;
        }
    }
}