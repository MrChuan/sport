package com.example.controller;

import com.example.service.impl.SysUserServiceImpl;
import com.example.util.Result;
import com.example.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chuan
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private SysUserServiceImpl userService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginvo){

        return userService.login(loginvo);
    }
}
