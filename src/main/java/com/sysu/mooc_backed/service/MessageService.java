package com.sysu.mooc_backed.service;

import com.sysu.mooc_backed.entity.Message;

import java.util.List;

/**
 * @Author: EdenChanIy
 * @Date: 2019/6/18 15:11
 */
public interface MessageService {
    List<Message> findList();

    void add(String message);

    void delete(int id);
}
