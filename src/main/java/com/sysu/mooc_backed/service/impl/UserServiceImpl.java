package com.sysu.mooc_backed.service.impl;

import com.sysu.mooc_backed.dao.UserMapper;
import com.sysu.mooc_backed.entity.User;
import com.sysu.mooc_backed.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Override
    public List<User> findList(){
        return userMapper.findList();
    }

    @Override
    public User findUserByName(String name){
        return userMapper.findUserByName(name);
    }

    @Override
    public void addUser(User user){
        userMapper.add(user);
    }

    @Override
    public void updateUser(User user){
        userMapper.update(user);
    }

    @Override
    public void deleteUser(int id){
        userMapper.delete(id);
    }
}
