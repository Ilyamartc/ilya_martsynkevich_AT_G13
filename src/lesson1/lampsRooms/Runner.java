package lesson1.lampsRooms;

public class Runner {
    public static void main(String[] args) {
        Lamp lamp1 = new Lamp(15, "LED");
        Lamp lamp2 = new Lamp(20, "LED");
        Lamp lamp3 = new Lamp(30, "LED");
        Lamp lamp4 = new Lamp(40, "LED");

        Room room1 = new Room(30);
        Room room2 = new Room(40);

        room1.installLamps(new Lamp[]{lamp1, lamp2});
        room2.installLamps(new Lamp[]{lamp3, lamp4});

        System.out.println(" Rooms 1: ");
        room1.lightOn();

        System.out.println(" Rooms 2: ");
        room2.lightOn();

        System.out.println(" Switch off: ");
        room1.lightOff();
        room2.lightOff();
    }
}
