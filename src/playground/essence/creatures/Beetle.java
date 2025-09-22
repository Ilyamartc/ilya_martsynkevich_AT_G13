package playground.essence.creatures;

public class Beetle extends Insect implements Crawlable {
    @Override
    public void crawl() {
        System.out.println(name + " is crawling");
    }

    public void nest(Carrot home) {
        int familyMembers = home.getMass() / this.mass;
        if (this.mass < home.getMass()) {
            System.out.printf("I am %s and I will nest there with %d my family members!%n", name, familyMembers);
        } else {
            System.out.println("This carrot is too small for nesting :(");
        }
    }
}
