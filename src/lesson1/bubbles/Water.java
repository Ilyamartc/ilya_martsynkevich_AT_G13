package lesson1.bubbles;

public abstract class Water {
    private String color;
    private String transparency;
    private String smell;
    private double temperature;
    public String getColor() {
        return color;
    }
    public String getSmell() {
        return smell;
    }
    public String getTransparency() {
        return transparency;
    }
    public double getTemperature() {
        return temperature;
    }
    public Water(String color, String transparency, String smell, double temperature) {
        this.color = color;
        this.transparency = transparency;
        this.smell = smell;
        this.temperature = temperature;
    }

}
//-- создать абстрактный класс Water
//-- у воды есть такие характеристики, цвет, прозрачность, запах, температура