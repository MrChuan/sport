package com.example.config.security.handle.service;

import com.example.entity.SysUser;
import com.example.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 实现UserDetailsService接口  实现自定义登录逻辑
 * @author chuan
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取用户对象
        SysUser user = sysUserMapper.findByUsername(username);
        log.info("UserDetailsServiceImpl----"+String.valueOf(user));
        if (null == user){
            throw new RuntimeException("用户名或密码错误！");
        }
        return user;
    }
}
