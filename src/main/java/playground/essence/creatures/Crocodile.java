package playground.essence.creatures;

import playground.essence.Crawlable;

public class Crocodile implements Crawlable {
    private int mass;
    private String name;

    public Crocodile(int mass, String name) {
        this.mass = mass;
        this.name = name;
    }

    @Override
    public void crawl(String direction, int distance) {
        System.out.printf("I am %s, my name is %s and I am crawling to %s for %d units%n",
                this.getClass().getSimpleName(), name, direction, distance);
        System.out.println("wr-wr-wrr-r..");
    }
}
