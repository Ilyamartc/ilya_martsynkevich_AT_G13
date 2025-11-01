package homework.day10;

import project.bubbles.Bubble;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BubblesVolumeRunner {
    public static void main(String[] args) {

        List<Bubble> bubbles = Arrays.asList(
                new Bubble(2, "CO2"),
                new Bubble(4, "O2"),
                new Bubble(5, "CO")
        );

        double totalVolume = bubbles.stream().filter(b -> b.getVolume() > 3).sorted(Comparator.comparing(Bubble::getGas)).map(b -> new Bubble(b.getVolume() * 3, b.getGas())).peek(System.out::println).mapToDouble(Bubble::getVolume).sum();

        System.out.println("Total volume of final bubbles: " + totalVolume);
    }
}
