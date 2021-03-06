package com.example.entity;

import com.example.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ApiModelProperty(value = "前端登陆用户名")
    private String name;

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
     * 本身不属于user中属性信息 在redis序列化中无法排序报错：Could not read JSON: Problem deserializing 'setterless' property ("authorities"): no way to handle typed deser with setterless yet
     * 解决：@JsonIgnore 添加  忽略序列化
     *
     * @return
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();

        if (roles != null && roles.size() > 0){
            roles.forEach(item ->{

                if (StringUtil.isNotEmpty(item.getCode())){
                    list.add(new SimpleGrantedAuthority("ROLE_" + item.getCode()));
                }
            });
        }

        if (permissions != null && permissions.size() > 0){
            //添加权限数据
            //如果数据库权限信息为空，会报错；
            //添加间接性判断
            permissions.forEach(item ->{
                if (item.getCode() !=null){
                    list.add(new SimpleGrantedAuthority(item.getCode()));
                }
            });
        }
        return list;
    }


    /**
     *账号是否过期
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * 账号是否锁定
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     *凭证是否过期
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * 是否被禁用
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return status;
    }
}
