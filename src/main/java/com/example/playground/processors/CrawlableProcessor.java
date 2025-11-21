package com.example.playground.processors;

import com.example.playground.essence.Crawlable;
import com.example.playground.utils.DirectionGenerator;
import com.example.playground.utils.DistanceGenerator;

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
