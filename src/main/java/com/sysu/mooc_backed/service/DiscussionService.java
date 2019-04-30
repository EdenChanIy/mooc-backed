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
    //根据作者id和课程id获取用户创建的讨论列表
    List<Object> findListByUidAndCidWithA(int uid, int cid);
    //根据用户id和课程id获取用户关注的讨论列表
    List<Object> findListByUidAndCidWithF(int uid, int cid);
    //根据讨论id获取讨论主题内容，回复列表及相关信息
    List<Object> findItemListById(int id);
    //根据讨论id获取讨论相关信息
    Map<String, Object> findDiscussionById(int id);
}
