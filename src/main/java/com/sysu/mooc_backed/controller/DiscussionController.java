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

    //3.2/3.3 根据用户id和课程id查看该课程下该用户的讨论列表，并通过type选择是自己发起的讨论，还是关注的讨论
    @RequestMapping("discussion/findListByUidAndCid")
    public Result findListByUidAndCid(String uid, String cid, String type){
        try{
            if(StringUtils.isEmpty(uid)) return Result.error("缺少用户id");
            int uidInt = Integer.parseInt(uid);
            int cidInt = 0;
            if(!StringUtils.isEmpty(cid)) cidInt = Integer.parseInt(cid);
            int typeInt = 0;
            if(!StringUtils.isEmpty(type)) typeInt = Integer.parseInt(type);

            Map<String, Object> result = new HashMap<>();
            if(typeInt==0){
                result.put("type", "用户创建的讨论");
                result.put("forum", discussionService.findListByUidAndCidWithA(uidInt, cidInt));
            }
            if(typeInt==1){
                result.put("type", "用户关注的讨论");
                result.put("forum", discussionService.findListByUidAndCidWithF(uidInt, cidInt));
            }
            return Result.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("网络异常");
        }
    }
}
