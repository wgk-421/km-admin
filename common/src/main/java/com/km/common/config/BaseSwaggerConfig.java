package com.km.common.config;

import com.km.common.data.SwaggerData;
import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Description: Swagger 3.0配置
 *
 * @Author GaoKunW
 * @Date 2021/2/3
 */
public abstract class BaseSwaggerConfig {
    @Bean
    public Docket createRestApi() {
        SwaggerData swaggerData = swaggerData();
        Docket docket = new Docket(DocumentationType.OAS_30)
                .apiInfo(this.apiInfo(swaggerData))
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerData.getApiBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .protocols(new LinkedHashSet<>(Arrays.asList("HTTPS", "HTTP")));
        // 安全验证开关
        if (swaggerData.isEnableSecurity()) {
            docket.securitySchemes(securitySchemes()).securityContexts(securityContexts());
        }
        return docket;
    }

    private List<SecurityScheme> securitySchemes() {
        ArrayList<SecurityScheme> auth = new ArrayList<>();
        auth.add(basicAuthScheme());
        auth.add(apiKeyScheme());
        return auth;
    }

    private SecurityScheme basicAuthScheme() {
        return HttpAuthenticationScheme.BASIC_AUTH_BUILDER.name("dc_auth").build();
    }

    private SecurityScheme apiKeyScheme() {
        return new ApiKey("Authorization", "Authorization", In.HEADER.toValue());
    }

    private List<SecurityContext> securityContexts() {
        SecurityContext securityContext = SecurityContext.builder()
                .securityReferences(Arrays.asList(basicAuthReference(), apiKeyReference()))
                .build();
        return Collections.singletonList(securityContext);
    }

    private SecurityReference basicAuthReference() {
        return new SecurityReference("dc_auth", new AuthorizationScope[0]);
    }

    private SecurityReference apiKeyReference() {
        return new SecurityReference("Authorization", new AuthorizationScope[]{new AuthorizationScope("global", "accessEverything")});
    }

    private ApiInfo apiInfo(SwaggerData swaggerData) {
        return new ApiInfoBuilder()
                .title(swaggerData.getTitle())
                .description(swaggerData.getDescription())
                .contact(new Contact(swaggerData.getContactName(), swaggerData.getContactUrl(),
                        swaggerData.getContactEmail()))
                .version(swaggerData.getVersion())
                .build();
    }

    /**
     * 自定义Swagger配置
     */
    public abstract SwaggerData swaggerData();
}
