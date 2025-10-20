package project.bubbles;

public class Runner {
    public static  void main(String[] args) {
        Bottle bottle1 = new Bottle(0.5);
        Bottle bottle2 = new Bottle(1.0);
        Bottle bottle3 = new Bottle(1.5);

        System.out.print("Opening bottle 1:");
        bottle1.open();
        System.out.println();

        System.out.print("Opening bottle 2:");
        bottle2.open();
        System.out.println();

        System.out.print("Opening bottle 3:");
        bottle3.open();
        System.out.println();
    }
    }
