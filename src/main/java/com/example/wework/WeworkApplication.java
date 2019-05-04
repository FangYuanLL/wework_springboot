package com.example.wework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.wework.dao")
public class WeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeworkApplication.class, args);
    }

}
