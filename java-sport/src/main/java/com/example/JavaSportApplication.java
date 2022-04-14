package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chuan
 */
@MapperScan("com.example.mapper")
@SpringBootApplication
public class JavaSportApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSportApplication.class, args);
    }

}
