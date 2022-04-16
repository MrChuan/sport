package com.example.mapper;

import com.example.entity.SysRole;
import com.example.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chuan
 */
@Mapper
public interface SysUserMapper {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    SysUser findByUsername(String username);


    /**
     * 查询用户角色
     * @param userId
     * @return
     */
    List<SysRole> findRoles(Long userId);

    /**
     * 查询所有数据
     * @return
     */
    List<SysUser> findAll();

}
