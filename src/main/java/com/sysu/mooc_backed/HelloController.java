package com.sysu.mooc_backed;

import com.sysu.mooc_backed.dao.UserMapper;
import com.sysu.mooc_backed.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping
    public String hello(){
        return "Hello Spring Boot!^_^";
    }
}
