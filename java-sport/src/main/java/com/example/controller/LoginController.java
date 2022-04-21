package com.example.controller;

import com.example.service.impl.SysUserServiceImpl;
import com.example.util.Result;
import com.example.util.SecurityUtil;
import com.example.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author chuan
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户使用接口")
public class LoginController {

    @Autowired
    private SysUserServiceImpl userService;

    @PostMapping("/login")
    @ApiOperation(value = "登录接口")
    public Result login(@RequestBody LoginVo loginvo){
        return userService.login(loginvo);
    }
    @GetMapping("/getInfo")
    @ApiOperation(value = "获取用户基本信息")
    public Result getUserInfo(Principal principal){
        if (null == principal){
            return Result.fail("请登录");
        }
        return Result.success("获取用户信息成功", SecurityUtil.getUser());
    }

    @ApiOperation(value = "用户退出")
    @GetMapping("/logout")
    public Result logout(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return Result.success("退出成功！");
    }
}
