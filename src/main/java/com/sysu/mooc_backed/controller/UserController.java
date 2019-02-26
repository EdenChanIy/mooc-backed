package com.sysu.mooc_backed.controller;

import com.sysu.mooc_backed.dao.UserMapper;
import com.sysu.mooc_backed.entity.User;
import com.sysu.mooc_backed.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/user/findList")
    public List<User> findList(){
        return userService.findList();
    }

    @RequestMapping("user/findUserByName")
    public User findListById(String name){
        return userService.findUserByName(name);
    }

    @RequestMapping("user/add")
    public void add(User user){
        userService.addUser(user);
    }

    @RequestMapping("user/update")
    public void update(User user){
        userService.updateUser(user);
    }

    @RequestMapping("user/delete")
    public String delete(int id){
        userService.deleteUser(id);
        return "delete successfully!";
    }
}
