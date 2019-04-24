package com.sysu.mooc_backed.service.impl;

import com.sysu.mooc_backed.dao.DiscussionMapper;
import com.sysu.mooc_backed.entity.Discussion;
import com.sysu.mooc_backed.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: EdenChanIy
 * @Date: 2019/4/24 18:37
 */
@Service
public class DiscussionServiceImpl implements DiscussionService {
    private DiscussionMapper discussionMapper;

    @Autowired
    DiscussionServiceImpl(DiscussionMapper discussionMapper){
        this.discussionMapper = discussionMapper;
    }

    @Override
    public Map<String, Object> findListByPid(int pid, int offset, int limit){
        Map<String, Object> result = new HashMap<>();
        List<Discussion> dlist = discussionMapper.findListByPId(pid, offset, limit);
        for(Discussion l:dlist){

        }
    }
}
