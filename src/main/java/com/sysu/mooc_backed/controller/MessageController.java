package com.sysu.mooc_backed.controller;

import com.sysu.mooc_backed.common.utils.Result;
import com.sysu.mooc_backed.entity.Message;
import com.sysu.mooc_backed.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: EdenChanIy
 * @Date: 2019/6/18 15:15
 */
@RestController
public class MessageController {
    private MessageService messageService;

    @Autowired
    MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @RequestMapping("/message/findList")
    public Result findList(){
        return Result.success(messageService.findList());
    }

    @RequestMapping("/message/add")
    public Result add(Message message){
        messageService.add(message);
        return Result.success("add successfully");
    }
}
