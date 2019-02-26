package com.sysu.mooc_backed.service;

import com.sysu.mooc_backed.entity.User;

import java.util.List;

public interface UserService {
    List<User> findList();
    User findUserByName(String name);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
}
