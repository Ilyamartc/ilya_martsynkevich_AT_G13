package lesson1.lampsRooms;

public class Room {
    private double square;
    Lamp[] lamps;
    public Room(double square) {
        this.square = square;
    }
    public double getSquare() {
        return square;
    }
    public void setSquare(double square) {
        this.square = square;
    }
    public void installLamps(Lamp[] lamps){
        this.lamps = lamps;
        System.out.println("Lamps are installed");
    }
    public void lightOn(){
        if (lamps != null){
            for (Lamp lamp : lamps){
                lamp.turnOn();
            }
        }
        else {
            System.out.println("Lamps are null");
        }
    }
    public void lightOff(){
        if (lamps != null){
            for (Lamp lamp : lamps){
                lamp.turnOff();
            }
        }
        else {
            System.out.println("Lamps are null");
        }
    }
}
