package com.sysu.mooc_backed.service;

import com.sysu.mooc_backed.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> findListByCategory2(int category2, int offset, int limit);
    int countFindListByCategory2(int category2);
}
