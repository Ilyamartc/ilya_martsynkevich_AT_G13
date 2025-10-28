package playground.runners;

import playground.essence.Crawlable;
import playground.essence.creatures.Crocodile;
import playground.essence.creatures.Beetle;
import playground.processors.CrawlableProcessor;

public class CrawlableProcessorRunner {
    public static void main(String[] args) {
        CrawlableProcessor processor = new CrawlableProcessor();

        Crawlable crawlableCrocodile = new Crocodile(1723, "Neel Crawlable");
        Crawlable crawlableBeetle = new Beetle(43, "Christmas Crawlable");

        Crocodile aCrocodile = new Crocodile(1723, "Neel Crocodile");
        Beetle aBeetle = new Beetle(43, "Christmas Beetle");

        Crawlable anonymousCrawlable = new Crawlable() {
            String name = "Anonymous";

            @Override
            public void crawl(String direction, int distance) {
                System.out.printf("I am %s, my name is %s and I am crawling to %s for %d units%n",
                        this.getClass().getSimpleName(), name, direction, distance);
            }
        };

        processor.runCrawlable(crawlableCrocodile);
        processor.runCrawlable(crawlableBeetle);
        processor.runCrawlable(aCrocodile);
        processor.runCrawlable(aBeetle);
        processor.runCrawlable(anonymousCrawlable);

        processor.runCrawlable(crawlableCrocodile, "никуда");
        processor.runCrawlable(aBeetle, "налево");

        processor.runCrawlable(crawlableBeetle, "назад", 37);

        processor.runCrawlable(new Crawlable() {
            String name = "Direct Anonymous";
            @Override
            public void crawl(String direction, int distance) {
                System.out.printf("I am %s, my name is %s and I am crawling to %s for %d units%n",
                        this.getClass().getSimpleName(), name, direction, distance);
            }
        }, "вниз", 37);
    }
}
