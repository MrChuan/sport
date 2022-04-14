package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chuan
 * swagger的bean实例为：Docket
 */
@Configuration
@EnableSwagger2

public class SwaggerConfig {
    /**
     * 这个方法的返回值交给Spring 管理
     * @return
     */
    @Bean
    public Docket createApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                 // select():返回 ApiSelectorBuilder 对象,设置哪些接口暴露给Swagger展示
                .select()
                //通过 apis()方法设置哪个包中内容被扫描:(第一种方式)扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                // (第二种方式)扫描指定包中的swagger注解
                //.apis(RequestHandlerSelectors.basePackage("com.hubiao.pay.merchant.controller"))
                // (第三种方式)扫描所有
                //.apis(RequestHandlerSelectors.any())

                .paths(PathSelectors.any())
                //通过对象调用 build()可以创建 Docket 对象
                .build()
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes());
    }

    /**
     * 该套 API 说明，包含作者、简介、版本、host、服务URL
     * @return
     * 接口信息设置：ApiInfo
     */
    private ApiInfo apiInfo(){
        return  new ApiInfoBuilder()
                .version("1.0.0")
                .title("个人运动管理平台")
                .contact(new Contact("1212","http://localhost:8080/doc.html","dddddd@qq.com"))
                .description("个人管理平台接口文档")
                .build();

    }
    private List<ApiKey> securitySchemes(){
        List<ApiKey> list = new ArrayList<>();
        ApiKey key = new ApiKey("Authorization","Authorization","Header");
        list.add(key);
        return list;
    }

    public List<SecurityContext> securityContexts(){
        List<SecurityContext> list = new ArrayList<>();
        list.add(getContextByPath("/hello/.*"));
        return list;
    }

    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext
                .builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> list = new ArrayList<>();
        AuthorizationScope scope = new AuthorizationScope("global","accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] =scope;
        list.add(new SecurityReference("Authorization", authorizationScopes));
        return list;
    }
}
