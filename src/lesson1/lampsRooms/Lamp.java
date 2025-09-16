package lesson1.lampsRooms;

public class Lamp {
    private int power;
    private String type;
    public Lamp(int power, String type) {
        this.power = power;
        this.type = type;
    }
    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void turnOn(){
        System.out.println("Lamp is on");
    }
    public void turnOff(){
        System.out.println("Lamp is off");
    }
}
