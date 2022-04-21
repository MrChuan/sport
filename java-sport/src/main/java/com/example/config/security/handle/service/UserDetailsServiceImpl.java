package com.example.config.security.handle.service;

import com.example.entity.SysMenu;
import com.example.entity.SysRole;
import com.example.entity.SysUser;
import com.example.mapper.SysUserMapper;
import com.example.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现UserDetailsService接口  实现自定义登录逻辑
 * @author chuan
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取用户对象
        SysUser user;
        if (redisUtil.hasKey("userInfo_" + username)){
            //如果存在则在缓存中取
          user =(SysUser) redisUtil.getValue("userInfo_" + username);
          redisUtil.expire("userInfo_" + username,5);
          log.info("缓存信息！");
        }else {//否则就去数据库查
            log.info("数据库信息信息！");
            user = sysUserMapper.findByUsername(username);
            //用户信息是否为空
            if (null == user){
                throw new UsernameNotFoundException("用户名或密码错误！");
            }
            //
            if (user.isAdmin()){
                user.setRoles(sysUserMapper.findRoles(null));
                user.setPermissions(sysUserMapper.findPermissions(null));
                //获取父级菜单
                List<SysMenu> menus = sysUserMapper.findMenus(null);
                //获取子菜单
                menus.forEach(item ->item.setChildren(sysUserMapper.findChildrenMenus(item.getId(),null)));
                user.setMenus(menus);
            }else {
                //非管理员需要查询
                user.setRoles(sysUserMapper.findRoles(user.getId()));
                user.setPermissions(sysUserMapper.findPermissions(user.getId()));
                List<SysMenu> menus = sysUserMapper.findMenus(user.getId());
                menus.forEach(item->item.setChildren(sysUserMapper.findChildrenMenus(item.getId(), user.getId())));
                user.setMenus(menus);
            }
            redisUtil.setValueTime("userInfo_" + username,user,5);
        }

        return user;
    }
}
