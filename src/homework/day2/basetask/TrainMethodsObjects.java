package lesson1.basetask;

public class TrainMethodsObjects {
    Mouse mouse = new Mouse("Lee", 199);
    public void processMouse(Mouse mouse) {
        mouse.printMouseDetails();
    }
    Souce souce = new Souce("Red", "ABC");
    public void processSouce(Souce souce) {
        souce.printSouceDetails();
    }
    Bee bee = new Bee("female", 2);
    public void processBee(Bee bee) {
        bee.printBeeDetails();
    }
    Obstacle obstacle = new Obstacle("P1", "AaaA");
    public void processObstacle(Obstacle obstacle) {
        obstacle.printObstacleDetails();
    }
    Pineapple pineapple = new Pineapple("Fao", 2);
    public void printPineappleDetails(Pineapple pineapple) {
        pineapple.printPineappleDetails();
    }
}