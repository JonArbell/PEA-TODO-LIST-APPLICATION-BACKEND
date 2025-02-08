package com.myapp.pea.Configurations;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;

@Configuration
public class Config {

    @Bean
    public CacheManager cacheManager() {
        var cacheManager = new SimpleCacheManager();

        var todoCache = new CaffeineCache("todos",
                Caffeine.newBuilder()
                        .expireAfterWrite(30, TimeUnit.MINUTES)
                        .maximumSize(1000)
                        .build());

        var listCache = new CaffeineCache("lists",
                Caffeine.newBuilder()
                        .expireAfterWrite(30, TimeUnit.MINUTES)
                        .maximumSize(1000)
                        .build());

        cacheManager.setCaches(java.util.List.of(todoCache,listCache));
        return cacheManager;
    }
}
