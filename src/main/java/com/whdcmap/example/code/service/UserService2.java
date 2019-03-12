package com.whdcmap.example.code.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.whdcmap.example.code.entity.User;


/***
*@purpose:另外定义用户实体类型
*@author:jianxiapc
*@since:2019年3月11日
***/
@Service
@CacheConfig(cacheNames = {"user"})
public class UserService2 {

    Set<User> users = new HashSet<User>();

    @CachePut(key = "#user.id")
    public User save(User user) {
        users.add(user);
        return user;
    }

    @CachePut(key = "#user.id")
    public User update(User user) {
        users.remove(user);
        users.add(user);
        return user;
    }

    @CacheEvict(key = "#user.id")
    public User delete(User user) {
        users.remove(user);
        return user;
    }

    @CacheEvict(allEntries = true)
    public void deleteAll() {
        users.clear();
    }

    @Cacheable(key = "#id")
    public User findById(final Long id) {
        System.out.println("cache miss, invoke find by id, id:" + id);
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

}