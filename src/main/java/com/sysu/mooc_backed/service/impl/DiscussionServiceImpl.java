package com.sysu.mooc_backed.service.impl;

import com.sysu.mooc_backed.dao.CourseMapper;
import com.sysu.mooc_backed.dao.DiscussionMapper;
import com.sysu.mooc_backed.dao.UserMapper;
import com.sysu.mooc_backed.entity.Discussion;
import com.sysu.mooc_backed.entity.DiscussionItem;
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

    @Override
    public List<Object> findListByUidAndCidWithA(int uid, int cid){
        List<Object> result = new ArrayList<>();
        List<Discussion> dlist = discussionMapper.findListByUidAndCidWithA(uid, cid);
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
    public List<Object> findListByUidAndCidWithF(int uid, int cid){
        List<Object> result = new ArrayList<>();
        List<Discussion> dlist = discussionMapper.findListByUidAndCidWithF(uid, cid);
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
    public List<Object> findItemListById(int id){
        List<Object> result = new ArrayList<>();
        List<DiscussionItem> itemList = discussionMapper.findItemListById(id);
        if(null!=itemList){
            for(DiscussionItem item:itemList){
                Map<String, Object> temp = new HashMap<>();
                temp.put("id", item.getId());
                temp.put("date", item.getCreateTime());
                temp.put("content", item.getContent());
                if(item.getParentId()!=0){
                    temp.put("replyName", userMapper.findAuthorInfoById(
                            discussionMapper.findItemByPid(
                                    item.getParentId()).getUserId()));
                }
                temp.put("replyer", userMapper.findAuthorInfoById(item.getUserId()));

                result.add(temp);
            }
        }

        return result;
    }

    @Override
    public Map<String, Object> findDiscussionById(int id){
        Map<String, Object> result = new HashMap<>();
        Discussion discussion = discussionMapper.findDiscussionById(id);
        result.put("id", discussion.getId());
        result.put("title", discussion.getTitle());
        result.put("createTime", discussion.getCreateTime());
        result.put("updateTime", discussion.getUpdateTime());
        result.put("replyCount", discussion.getReplyCount());
        result.put("pageViews", discussion.getView());
        result.put("likeCount", discussion.getLikeCount());
        if(null!=userMapper.findAuthorInfoById(discussion.getAuthorId())){
            result.put("creator", userMapper.findAuthorInfoById(discussion.getAuthorId()));
        }
        if(null!=courseMapper.findInfoByDiscussionId(discussion.getAuthorId())){
            result.put("createPosition", courseMapper.findInfoByDiscussionId(discussion.getAuthorId()));
        }

        return result;
    }
}
