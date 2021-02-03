package com.km.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Description: MyBatisConfig
 *
 * @Author GaoKunW
 * @Date 2021/2/3
 */
@Configuration
@MapperScan("com.km.common.generate.mapper")
public class MyBatisConfig {
}
