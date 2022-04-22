package com.example.service.impl;

import com.example.config.security.handle.service.UserDetailsServiceImpl;
import com.example.entity.SysRole;
import com.example.entity.SysUser;
import com.example.mapper.SysUserMapper;
import com.example.service.SysUserService;
import com.example.util.Result;
import com.example.util.TokenUtil;
import com.example.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chuan
 */
@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {


    //此处如果使用@Autowired，则会报错No beans of 'sysUserMapper' type found
    //需要在mapper上加上@Mapper

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHead}")
    private  String tokenHead;

    @Override
    public Result findAll() {
        log.info("获取用户全部信息！");
        return Result.success("获取用户全部信息",sysUserMapper.findAll());
    }

    @Override
    public Result login(LoginVo loginvo) {
        log.info("1.登录");
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginvo.getUsername());
        log.info("2.判断账号密码");
        if (null == userDetails || !passwordEncoder.matches(loginvo.getPassword(),userDetails.getPassword())){
            return Result.fail("账号或者密码错误，请重新输入！");
        }
        if (!userDetails.isEnabled()){
            return Result.fail("账号禁用，联系管理员");
        }
        log.info("登录成功,在security对象中存入登录者信息");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        //
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        log.info("根据登陆信息获取token");
        //获取token 需要借助jwt工具

        String token = tokenUtil.generateToken(userDetails);

        Map<String ,String> map = new HashMap<>(16);
        map.put("tokenHead",tokenHead);
        map.put("token",token);

        return Result.success("登录成功！",map);
    }

    @Override
    public SysUser findByUsername(String username) {
        return sysUserMapper.findByUsername(username);
    }

    @Override
    public List<SysRole> findRolesByUserId(Long userId) {
        return sysUserMapper.findRoles(userId);
    }


}
