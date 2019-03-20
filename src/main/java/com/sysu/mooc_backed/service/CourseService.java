package com.sysu.mooc_backed.service;

import com.sysu.mooc_backed.entity.Course;

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
}
