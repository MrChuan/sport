package com.example.service;

import com.example.entity.SysRole;
import com.example.entity.SysUser;
import com.example.util.Result;
import com.example.vo.LoginVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户操作逻辑业务接口
 * @author chuan
 */
@Service
public interface SysUserService {
    /**
     * 获取所有用户信息
     */
    Result findAll();

    /**
     * 登陆信息
     * @param loginvo 登录参数：账号和密码
     * @return 返回token ,用token去获取资源
     */
    Result login(LoginVo loginvo);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return 返回用户数组
     */
    SysUser findByUsername(String username);


    /**
     * 根据用户id查询角色信息
     * @param userId
     * @return
     */
    List<SysRole> findRolesByUserId(Long userId);
}
