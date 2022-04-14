package com.example.config.security.contents;

/**
 * 白名单
 * @author chuan
 */
public class SecurityContents {

    public static final String[] WHITE_LIST = {
            //后端登陆接口
            "/user/login",


            //swagger相关
            "/doc.html",
            "/webjars/**",
            "/swagger-resources/**",
            "/v2/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html"

    };
}
