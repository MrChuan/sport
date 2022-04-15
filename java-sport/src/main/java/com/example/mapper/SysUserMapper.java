package com.example.mapper;

import com.example.entity.SysRole;
import com.example.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chuan
 */
@Mapper
public interface SysUserMapper {


    SysUser findByUsername(String username);

    /**
     * 根据用户ID查询权限信息
     */


    List<SysRole> findRoles(Long userId);



    /**
     * 查询所有数据
     * @return
     */
    List<SysUser> findAll();

}
