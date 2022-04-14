package com.example.config.security.handle;

import com.example.util.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author chuan
 */
@Component
public class JwtAccessDeniedHandle implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //设置放回状态码
        response.setStatus(403);
        //设置字符集
        response.setCharacterEncoding("UTF-8");
        //响应的格式
        response.setContentType("application/json");
        //输出结果
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(Result.fail("权限不足！")));
        //强制将缓冲区中的数据发送出去
        writer.flush();
        //关闭流
        writer.close();
    }
}
