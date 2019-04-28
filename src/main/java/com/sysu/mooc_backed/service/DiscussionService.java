package com.sysu.mooc_backed.service;

import java.util.List;
import java.util.Map;

/**
 * @Author: EdenChanIy
 * @Date: 2019/4/24 17:34
 */
public interface DiscussionService {
    //根据课时id获取讨论列表
    List<Object> findListByPid(int pid, int offset, int limit);
    //根据课时id获取讨论列表COUNT
    int findListCountByPid(int pid);
}
