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
