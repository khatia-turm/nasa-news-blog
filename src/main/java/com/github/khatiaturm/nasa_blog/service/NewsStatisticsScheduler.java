package com.github.khatiaturm.nasa_blog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.khatiaturm.nasa_blog.repository.NewsPostRepository;
// tells spring to create and manage an instance of this class
//if class was created manually, sping would not automatically schedule its methods
@Component
public class NewsStatisticsScheduler {

    // only this class can directly use this field
    // but cuz it's static, one logger is shared by every instance of this class
    // the repository already inherits this method from jpa Repository, so no custom query was required
    private static final Logger log = LoggerFactory.getLogger(NewsStatisticsScheduler.class);

    private final NewsPostRepository newsPostRepository;

    // spring automatically provides the real repository when creating the scheduler.
    public NewsStatisticsScheduler(NewsPostRepository newsPostRepository) {
        this.newsPostRepository = newsPostRepository;
    }

    // run the following method automatically
    // use string based settings so values come from properties
    @Scheduled(
            fixedRateString = "${news.statistics.interval-ms:300000}",
            initialDelayString = "${news.statistics.initial-delay-ms:10000}")
    public void logNewsPostCount() {
        log.info("Scheduled news statistics: {} posts available", newsPostRepository.count());
    }
}
