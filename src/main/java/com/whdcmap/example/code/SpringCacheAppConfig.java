package com.whdcmap.example.code;

import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.whdcmap.example.code.cache.CustomCacheResolver;

/***
*@purpose:缓存配置
*@author:jianxiapc
*@since:2019年3月11日
***/
@Configuration
@ComponentScan(basePackages = "com.whdcmap.example.code.service")
@EnableCaching(proxyTargetClass = true)
public class SpringCacheAppConfig implements CachingConfigurer {
	@Bean
    @Override
    public CacheManager cacheManager() {
        javax.cache.CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
        MutableConfiguration<Object, Object> mutableConfiguration = new MutableConfiguration<Object, Object>();
        mutableConfiguration.setStoreByValue(false);  // otherwise value has to be Serializable
        cacheManager.createCache("user", mutableConfiguration);
        cacheManager.createCache("user2", mutableConfiguration);
        cacheManager.createCache("user3", mutableConfiguration);

        JCacheCacheManager jCacheCacheManager = new JCacheCacheManager(cacheManager);
        return jCacheCacheManager;
    }

    @Bean
    @Override
    public CacheResolver cacheResolver() {
        return new CustomCacheResolver();
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                System.out.println("cache get error");
            }

            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                System.out.println("cache put error");
            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                System.out.println("cache evict error");
            }

            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                System.out.println("cache clear error");
            }
        };
    }	
}
