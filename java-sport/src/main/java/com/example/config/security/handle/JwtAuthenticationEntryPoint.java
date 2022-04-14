package com.example.config.security.handle;

import com.example.util.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 当前用户未登录和token过期时的请况下访问资源
 * @author chuan
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //设置放回状态码
        response.setStatus(401);
        //设置字符集
        response.setCharacterEncoding("UTF-8");
        //响应的格式
        response.setContentType("application/json");
        //输出结果
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(Result.fail("您尚未登陆，请登录！！")));
        //强制将缓冲区中的数据发送出去
        writer.flush();
        //关闭流
        writer.close();

    }
}
