package com.km.main.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description:
 * @Author: GaoKunW
 * @Date: 2020/12/6 23:39
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.km.main.admin.mbgen.mapper")
public class MyBatisConfig {
}
