package com.sysu.mooc_backed.service;

import com.sysu.mooc_backed.entity.User;

import java.util.List;

public interface UserService {
    List<User> findList();
    User findUserByName(String name);

    //根据用户id获取用户信息
    User findUserById(int id);

    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);


    //通过用户id获取用户兴趣列表
    List<Integer> findInterestListByUserId(int userId);
}
