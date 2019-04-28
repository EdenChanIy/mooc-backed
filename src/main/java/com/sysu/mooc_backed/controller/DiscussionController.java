package com.sysu.mooc_backed.controller;

import com.sysu.mooc_backed.common.utils.Result;
import com.sysu.mooc_backed.common.utils.StringUtils;
import com.sysu.mooc_backed.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: EdenChanIy
 * @Date: 2019/4/27 17:27
 */
@RestController
public class DiscussionController {
    private final DiscussionService discussionService;

    @Autowired
    public DiscussionController(DiscussionService discussionService){
        this.discussionService = discussionService;
    }

    //3.1 分页获取课程的讨论
    @RequestMapping("/discussion/findListByPid")
    public Result findListByPid(String pid, String page, String limit){
        try{
            if(StringUtils.isEmpty(pid)){
                return Result.error("缺少课时pid");
            }
            int limitInt = StringUtils.isEmpty(limit)?10:Integer.parseInt(limit);
            int offset = StringUtils.isEmpty(page)?0:(Integer.parseInt(page)-1)*limitInt;
            int pidInt = Integer.parseInt(pid);

            Map<String, Object> result = new HashMap<>();
            result.put("forum", discussionService.findListByPid(pidInt, offset, limitInt));
            result.put("forumNum", discussionService.findListCountByPid(pidInt));

            return Result.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("网络异常");
        }
    }
}
