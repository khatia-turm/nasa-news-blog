package com.github.khatiaturm.nasa_blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NasaBlogApplication {

    private static final Logger log = LoggerFactory.getLogger(NasaBlogApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(NasaBlogApplication.class, args);
        log.info("NASA Blog application started");
    }

}
