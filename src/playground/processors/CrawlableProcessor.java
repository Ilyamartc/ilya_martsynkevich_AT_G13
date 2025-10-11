package playground.processors;

import playground.essence.Crawlable;
import playground.utils.DirectionGenerator;
import playground.utils.DistanceGenerator;

public class CrawlableProcessor {

    public void runCrawlable(Crawlable crawlable) {
        String direction = DirectionGenerator.generateDirection();
        int distance = DistanceGenerator.generateDistance();
        crawlable.crawl(direction, distance);
    }

    public void runCrawlable(Crawlable crawlable, String direction) {
        int distance = DistanceGenerator.generateDistance();
        crawlable.crawl(direction, distance);
    }

    public void runCrawlable(Crawlable crawlable, String direction, int distance) {
        crawlable.crawl(direction, distance);
    }
}
