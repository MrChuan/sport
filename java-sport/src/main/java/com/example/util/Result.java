package com.example.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chuan
 */
@Data
public class Result implements Serializable {
    /**
     * 响应给前端是否成功的标识
     */
    private boolean flag;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 响应数据
     */
    private Object data;

    public Result() {
    }

    public Result(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    /**
     * 响应成功
     */
    public static Result success(String message , Object data){
        return new Result(true,message,data);
    }

    public static Result success(String message){
        return new Result(true,message);
    }

    /**
     * 响应失败
     */
    public static Result fail(String message){
        return new Result(false,message);
    }


}
