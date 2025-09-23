package playground.essence.creatures;

public class Crocodile extends Vertebrata implements Crawlable {
    public Crocodile(int mass, String name) {
        super(mass, name);
    }
    @Override
    public void crawl() {
        System.out.println(name + " is crawling");
    }
}
