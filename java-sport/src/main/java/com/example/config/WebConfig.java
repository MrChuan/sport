package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域处理的配置类
 * @author chuan
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                //允许访问的路径
                .addMapping("/**")
                //允许请求的来源
                .allowedOrigins("http://localhost:8081")
                //允许跨域的访问的方法
                .allowedMethods("GET","POST","DELETE","PUT","OPTION")
                //允许存在请求头
                .allowCredentials(true)
                //允许访问的最大响应时间
                .maxAge(3600);

    }
}
