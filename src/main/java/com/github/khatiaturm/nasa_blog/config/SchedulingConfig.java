package com.github.khatiaturm.nasa_blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

// makes this class spring config class
@Configuration
// activates spring's scheduling system
/*
* without @EnableScheduling, the scheduled method
* would behave like an ordinary method and would never run automatically.*/
@EnableScheduling
public class SchedulingConfig {
}
