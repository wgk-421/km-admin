package com.km.demo.config;

import com.km.common.config.BaseSwaggerConfig;
import com.km.common.data.SwaggerData;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * Description: Swagger配置类
 *
 * @Author GaoKunW
 * @Date 2021/1/28
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig extends BaseSwaggerConfig {
    @Override
    public SwaggerData swaggerData() {
        return SwaggerData.builder().apiBasePackage("com.km.demo.controller").description("demo").title("demo " +
                "test").version("1.0").contactEmail("wanggaokun@wo.cn").contactUrl("####").contactName("GaoKUnW").enableSecurity(false).build();
    }
}
