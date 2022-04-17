package com.example.exception;

import com.example.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;



@Slf4j
/**
 * @author chuan
 * 在spring 3.2中，新增了@ControllerAdvice 注解，可以用于定义@ExceptionHandler、@InitBinder、@ModelAttribute，并应用到所有@RequestMapping中。
 */
@RestControllerAdvice
public class GlobleException {

    //INTERNAL_SERVER_ERROR(500, "Internal Server Error")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = RuntimeException.class)
    public Result exception(RuntimeException e){
        //e.printStackTrace();
        log.error("系统运行时异常--->{}",e.getMessage());
        return Result.fail(e.getMessage());
    }

    //FORBIDDEN(403, "Forbidden"),
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = AccessDeniedException.class)
    public Result exception(AccessDeniedException e){
        log.error("权限不足-->{}",e.getMessage());
        return Result.fail("权限不足！");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = UsernameNotFoundException.class)
    public Result exception(UsernameNotFoundException e){
        log.error("用户名没有找到-->{}",e.getMessage());
        return Result.fail(e.getMessage());
    }


}
