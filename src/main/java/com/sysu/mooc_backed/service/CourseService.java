package com.sysu.mooc_backed.service;

import com.sysu.mooc_backed.entity.Course;
import com.sysu.mooc_backed.entity.UserAndCourse;

import java.util.List;

public interface CourseService {
    //获取一级分类id列表
    List<Integer> findCategory1List();

    //根据一级分类id查询对应每个二级分类id的第一个课程
    List<Course> findListByCategory1(int category1);

    //根据二级分类获取课程
    List<Course> findListByCategory2(int category2, int offset, int limit);

    //Count根据二级分类获取课程
    int countFindListByCategory2(int category2);

    //根据兴趣列表获取最多5个推荐课程
    List<Course> findRecommendListByInterests(List<Integer> interests);

    //根据课程id获取课程信息
    Course findCourseById(int id);

    //根据课程id和用户id获取用户课程关系信息
    UserAndCourse findRelByUidAndCid(int userId, int courseId);
}
