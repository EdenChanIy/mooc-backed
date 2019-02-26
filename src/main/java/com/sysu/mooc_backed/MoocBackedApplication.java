package com.sysu.mooc_backed;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sysu.mooc_backed.dao")
public class MoocBackedApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoocBackedApplication.class, args);
    }

}
