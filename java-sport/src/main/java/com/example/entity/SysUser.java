package com.example.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author chuan
 */
@Data
public class SysUser implements UserDetails {

    private long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "微信获取头像")
    private String avatar;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "获取微信小程序id")
    private String openId;

    @ApiModelProperty(value = "状态")
    private boolean status;

    @ApiModelProperty(value = "是否是管理权限")
    private boolean admin;

    @ApiModelProperty(value = "电话号码")
    private String phonenumber;

    @ApiModelProperty(value = "角色列表")
    private List<SysRole> roles;

    /**
     * 角色对应的菜单列表
     */
    @ApiModelProperty(value = "菜单信息")
    private List<SysMenu> menus;
    /**
     * 数据权限
     */
    @ApiModelProperty(value = "用户对应的权限数据")
    private List<SysPermission> permissions;

    /**
     * 权限数据
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        if (roles != null && roles.size() > 0){
            roles.forEach(item ->list.add(new SimpleGrantedAuthority("ROLE_" + item.getCode())));
        }

        if (permissions != null && permissions.size() > 0){
            //添加权限数据
            permissions.forEach(item -> list.add(new SimpleGrantedAuthority(item.getCode())));
        }
        return list;
    }


    /**
     *账号是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * 账号是否锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     *凭证是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * 是否被禁用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return status;
    }
}
