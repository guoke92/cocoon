package io.cocoon.cache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import io.cocoon.cache.config.properties.CacheCaffeineProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-11-11 16:59
 **/
@Slf4j
@Configuration
@EnableConfigurationProperties(CacheCaffeineProperties.class)
public class CacheConfig {

    @Autowired
    @Qualifier("cacheExecutor")
    private Executor executor;

    @Bean("caffeine")
    public CacheManager caffeineCacheManager(CacheCaffeineProperties cacheCaffeineProperties) {

        Caffeine caffeine = Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(10000)
                .executor(executor)
                .expireAfterAccess(cacheCaffeineProperties.getExpireSecond(), TimeUnit.SECONDS)
                .removalListener((k,v,cause) -> log.info("key: {} is removed, old value: {}, cause: {}", k, v, cause));

        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeine);
        cacheManager.setAllowNullValues(false);
        return cacheManager;
    }

}
