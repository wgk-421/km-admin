package com.km.common.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Description:
 *
 * @author GaoKunW
 * @date 2021/2/7 23:18
 */
public class RedisCacheUtils {
    @Autowired
    public RedisTemplate redisTemplate;

}
