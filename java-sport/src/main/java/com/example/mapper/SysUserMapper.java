package com.example.mapper;

import com.example.entity.SysMenu;
import com.example.entity.SysPermission;
import com.example.entity.SysRole;
import com.example.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * @Param("userId") mybatis 注解
     */
    List<SysRole> findRoles(@Param("userId") Long userId);

    /**
     * 获取用户菜单
     * @param userId
     * @return
     */
    List<SysMenu> findMenus(@Param("userId") Long userId);


    /**
     * 根据父类id 和 用户id  获取用户子菜单
     * @param parentId
     * @param userId
     * @return
     */
    List<SysMenu> findChildrenMenus(@Param("parentId") Long parentId, @Param("userId") Long userId);

    /**
     * 根据用户Id查询用户权限信息
     * @param userId
     * @return
     */
    List<SysPermission> findPermissions(@Param("userId") Long userId);

    /**
     * 查询所有数据
     * @return
     */
    List<SysUser> findAll();

}
