package com.sysu.mooc_backed.service.impl;

import com.sysu.mooc_backed.dao.MessageMapper;
import com.sysu.mooc_backed.entity.Message;
import com.sysu.mooc_backed.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: EdenChanIy
 * @Date: 2019/6/18 15:12
 */
@Service
public class MessageServiceImpl implements MessageService {

    private MessageMapper messageMapper;

    @Autowired
    MessageServiceImpl(MessageMapper messageMapper){
        this.messageMapper = messageMapper;
    }

    @Override
    public List<Message> findList(){
        return messageMapper.findList();
    }

    @Override
    public void add(String message){
        messageMapper.add(message);
    }

    @Override
    public void delete(int id){
        messageMapper.delete(id);
    }
}
