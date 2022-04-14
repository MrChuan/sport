package com.example.util;

import com.example.entity.SysUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 用于获取当前登陆用户的基本信息
 * @author chuan
 */
public class SecurityUtil {

    /**
     * 从Security主体信息中获取用户信息
     * @return
     */
    public static SysUser getUser(){
        return (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 在Security中获取用户名
     * @return
     */
    public static String getUsername(){
        return getUser().getUsername();
    }

}
