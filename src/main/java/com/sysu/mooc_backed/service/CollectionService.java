package com.sysu.mooc_backed.service;

import com.sysu.mooc_backed.entity.Collections;

import java.util.List;

/**
 * @Author: EdenChanIy
 * @Date: 2019/3/21 0:50
 */
public interface CollectionService {
    //根据用户id和课程id获取收藏记录
    Collections findRecordByUserIdAndCourseId(int userId, int courseId);

    //根据用户id和课程id增加收藏记录
    void addCollectionByUserIdAndCourseId(int userId, int courseId);

    //根据用户id和课程id删除收藏记录
    void deleteCollectionByUserIdAndCourseId(int userId, int courseId);

    //根据用户id获取收藏课程列表
    List<Integer> findRecordsByUserId(int userId);
}
