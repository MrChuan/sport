package com.example.util;

import com.example.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 用于获取当前登陆用户的基本信息
 * @author chuan
 */
@Slf4j
public class SecurityUtil {

    /**
     * 从Security主体信息中获取用户信息
     * @return
     */
    public static SysUser getUser(){
        SysUser user =  (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setPassword(null);
        user.setName(user.getUsername());
        return user;

    }

    /**
     * 在Security中获取用户名
     * @return
     */
    public static String getUsername(){
        return getUser().getUsername();
    }

}
