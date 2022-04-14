package com.example.config.security;

import com.example.config.security.contents.SecurityContents;
import com.example.config.security.handle.JwtAccessDeniedHandle;
import com.example.config.security.handle.JwtAuthenticationEntryPoint;
import com.example.config.security.handle.JwtAuthenticationFilter;
import com.example.config.security.handle.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 权限基本配置
 * @author chuan
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAccessDeniedHandle jwtAccessDeniedHandle;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;




    /**
     * 一般用来配置白名单：可以没有权限也可以访问资源
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers(SecurityContents.WHITE_LIST);
    }

    /**
     *security 核心配置
     * @param http
     * @throws Exception
     *
     * 复写这个方法来配置 HttpSecurity
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //1.实用jwt,首先关闭跨域攻击
        http.csrf().disable();

        //2.关闭session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //3.请求都需要进行认证之后才能访问，除白名单
        http.authorizeRequests().anyRequest().authenticated();
        //4.关闭缓存
        http.headers().cacheControl();
        //5.token过滤器配置
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        //6.没有登陆，没有权限访问自定义返回结果
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(jwtAccessDeniedHandle);
    }

    /**
     * 自定义逻辑配置
     * @param auth
     * @throws Exception
     *
     * 通过AuthenticationManager()方法的默认实现尝试获取一个AuthenticationManager。
     * 如果被复写，应该使用AuthenticationManagerBuilder来指定一个AuthenticationManager
     * 实现内存验证、LADP验证、基于JDBC验证、添加UserDetailService、添加AuthenticationProvider
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




}