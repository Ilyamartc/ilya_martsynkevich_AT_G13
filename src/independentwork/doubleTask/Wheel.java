package lesson1.doubleTask;

public class Wheel {
    private final  double diameter = 15.0;
    private String material;
    public double getDiameter() {
        return diameter;
    }
    public String getMaterial() {
        return material;
    }
    public void inflate(){
        System.out.println("Покрышка надута!");
    }
    public Wheel(String material) {
        this.material = material;
    }
}
