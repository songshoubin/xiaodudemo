package com.xiaodu.cache.service;

import com.xiaodu.cache.vo.RedisVo;
import com.xiaodu.common.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis缓存Service实现类
 *
 * @author shen
 * @date 2019-5-13
 */
@Service
public class RedisService {
    @Value("${Pcode.expiration}")
    private Long expiration;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    JedisPool pool;

    /**
     * 根据key获取缓存
     *
     * @param key
     * @return String
     */
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.get(key);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    /**
     * 新增缓存
     *
     * @param redisVo
     * @return void
     */
    public void save(RedisVo redisVo) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.set(redisVo.getKey(), redisVo.getValue());
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    /**
     * 根据key删除缓存
     *
     * @param key
     * @return void
     */
    public void delete(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.del(key);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    /**
     * 清空所有缓存
     *
     * @return void
     */
    public void flushdb() {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.flushAll();
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    /**
     * 分页
     *
     * @param key
     * @param pageable
     * @return Page
     */
    public Page findByKey(String key, Pageable pageable) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            List<RedisVo> list = new ArrayList<>();

            if (!key.equals("*")) {
                key = "*" + key + "*";
            }
            for (String s : jedis.keys(key)) {
                RedisVo redisVo = new RedisVo(s, jedis.get(s));
                list.add(redisVo);
            }
            Page<RedisVo> page = new PageImpl<RedisVo>(
                    PageUtil.toPage(pageable.getPageNumber(), pageable.getPageSize(), list),
                    pageable,
                    list.size());
            return page;
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    /**
     * 获取验证码
     *
     * @param key
     * @return
     */
    public String getCodeVal(String key) {
        try {
            String value = redisTemplate.opsForValue().get(key).toString();
            return value;
        }catch (Exception e){
            return "";
        }
    }

    /**
     * 保存验证码
     *
     * @param key
     * @param val
     */
    public void saveCode(String key, Object val) {
        redisTemplate.opsForValue().set(key, val);
        redisTemplate.expire(key, expiration, TimeUnit.MINUTES);
    }
}
