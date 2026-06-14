package com.github.khatiaturm.nasa_blog.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

// makes this a Spring configuration class
@Configuration
//activates support for @chacheable and @CacheEvent
@EnableCaching
public class CacheConfig {
}
