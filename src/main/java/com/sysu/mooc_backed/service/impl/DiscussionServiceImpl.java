package com.sysu.mooc_backed.service.impl;

import com.sysu.mooc_backed.dao.CourseMapper;
import com.sysu.mooc_backed.dao.DiscussionMapper;
import com.sysu.mooc_backed.dao.UserMapper;
import com.sysu.mooc_backed.entity.Discussion;
import com.sysu.mooc_backed.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: EdenChanIy
 * @Date: 2019/4/24 18:37
 */
@Service
public class DiscussionServiceImpl implements DiscussionService {
    private final DiscussionMapper discussionMapper;
    private final UserMapper userMapper;
    private final CourseMapper courseMapper;

    @Autowired
    DiscussionServiceImpl(DiscussionMapper discussionMapper,
                            UserMapper userMapper,
                                CourseMapper courseMapper){
        this.discussionMapper = discussionMapper;
        this.userMapper = userMapper;
        this.courseMapper = courseMapper;
    }

    @Override
    public List<Object> findListByPid(int pid, int offset, int limit){
        List<Object> result = new ArrayList<>();
        List<Discussion> dlist = discussionMapper.findListByPId(pid, offset, limit);
        if(null!=dlist){
            for(Discussion l:dlist){
                Map<String, Object> temp = new HashMap<>();
                temp.put("id", l.getId());
                temp.put("title", l.getTitle());
                temp.put("createTime", l.getCreateTime());
                temp.put("updateTime", l.getUpdateTime());
                temp.put("replyCount", l.getReplyCount());
                temp.put("pageViews", l.getView());
                temp.put("likeCount", l.getLikeCount());
                if(null!=userMapper.findAuthorInfoById(l.getAuthorId())){
                    temp.put("creator", userMapper.findAuthorInfoById(l.getAuthorId()));
                }
                if(null!=courseMapper.findInfoByDiscussionId(l.getAuthorId())){
                    temp.put("createPosition", courseMapper.findInfoByDiscussionId(l.getAuthorId()));
                }
                result.add(temp);
            }
        }
        return result;
    }

    @Override
    public int findListCountByPid(int pid){
        return discussionMapper.findListCountByPid(pid);
    }
}
