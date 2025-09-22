package playground.essence.creatures;

public class Crocodile extends Vertebrata implements Crawlable {
    @Override
    public void crawl() {
        System.out.println(name + " is crawling");
    }
}
