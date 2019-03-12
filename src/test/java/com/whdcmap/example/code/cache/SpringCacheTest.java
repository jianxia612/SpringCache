package com.whdcmap.example.code.cache;

import java.io.IOException;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;

import org.junit.Test;
import org.springframework.cache.Cache;
import org.springframework.cache.jcache.JCacheCacheManager;
import junit.framework.Assert;

import com.whdcmap.example.code.entity.User;

public class SpringCacheTest {

    @Test
    public void test() throws IOException {
        CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
        MutableConfiguration<Object, Object> mutableConfiguration = new MutableConfiguration<Object, Object>();
        mutableConfiguration.setStoreByValue(false);  // otherwise value has to be Serializable
        cacheManager.createCache("user", mutableConfiguration);

        JCacheCacheManager jCacheCacheManager = new JCacheCacheManager(cacheManager);

        Long id = 1L;
        User user = new User(id, "ye", "swiftleaf612@gmail.com");

        //根据缓存名字获取Cache
        Cache cache = jCacheCacheManager.getCache("user");

        //往缓存写数据(如果不存在 )
        cache.putIfAbsent(id, user);
        //从缓存读数据
        Assert.assertNotNull(cache.get(id, User.class));
    }

}
