package com.xiaodu.cache.config;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.nio.charset.Charset;

/**
 * String序列化
 *
 * @author shen
 * @date 2019-4-26
 */
public class StringRedisSerializer implements RedisSerializer<Object> {

    private final Charset charset;

    private final String target = "\"";

    private final String replacement = "";

    public StringRedisSerializer() {
        this(Charset.forName("UTF8"));
    }

    public StringRedisSerializer(Charset charset) {
        Assert.notNull(charset, "Charset must not be null!");
        this.charset = charset;
    }

    @Override
    public String deserialize(byte[] bytes) {
        return (bytes == null ? null : new String(bytes, charset));
    }

    @Override
    public byte[] serialize(Object object) {
        String string = JSON.toJSONString(object);
        if (string == null) {
            return null;
        }
        string = string.replace(target, replacement);
        return string.getBytes(charset);
    }
}
