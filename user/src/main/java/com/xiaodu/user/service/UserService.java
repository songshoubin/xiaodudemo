package com.xiaodu.user.service;

import com.xiaodu.user.model.TestUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;

/**
 * 用户接口
 *
 * @author: songshoubin
 * @date: 2019-07-15
 */
@CacheConfig(cacheNames = "user")
public interface UserService {
    /**
     * 添加用户
     *
     * @param testUser
     * @return
     */
    @CacheEvict(allEntries = true)
    TestUser addUser(TestUser testUser);
}
