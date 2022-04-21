package com.example.controller;

import com.example.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chuan
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public Result test(){
        //return "你好，程序开始。";
        return Result.success("信息返回成功！","你好！");
    }
}
