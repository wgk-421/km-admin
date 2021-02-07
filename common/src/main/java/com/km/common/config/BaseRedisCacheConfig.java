package com.km.common.config;

import com.km.common.utils.redis.RedisCacheUtils;
import org.springframework.context.annotation.Bean;

/**
 * Description: redis缓存配置
 *
 * @author GaoKunW
 * @date 2021/2/7 23:29
 */
public class BaseRedisCacheConfig {
    @Bean
    public RedisCacheUtils redisService(){
        return new RedisCacheUtils();
    }
}
