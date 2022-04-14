package com.example.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author chuan
 *
 * id bigint(20) not null primary key auto_increment comment'主键',
 * 	user_name varchar(20) comment'登录名',
 * 	password varchar(20) comment'密码',
 * 	nick_name varchar(20) comment'昵称',
 * 	sex tinyint(1) comment'性别(0男,1女)',
 * 	avatar varchar(100) comment'用户头像',
 * 	address varchar(100) comment'地址',
 * 	open_id varchar(100) comment'微信小程序openID',
 * 	status tinyint(1) comment'状态，是否禁用',
 * 	amdin tinyint(1) comment'是否是管理员',
 * 	phone_number varchar(20) comment'手机号'
 *
 */
@Data
public class SysUser implements UserDetails {

    private long id;

    private String username;

    private String password;

    private String nickname;

    private Integer sex;

    private String avatar;

    private String address;

    private String openId;

    private boolean status;

    private boolean admin;

    private String phonenumber;

    private List<SysRole> roles;

    /**
     * 权限数据
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        roles.forEach(item ->{
            list.add(new SimpleGrantedAuthority("ROLE_" + item.getCode()));
        });
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
